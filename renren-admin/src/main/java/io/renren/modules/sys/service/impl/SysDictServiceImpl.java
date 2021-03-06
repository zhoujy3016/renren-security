/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.dictionary.annotation.DictionaryCache;
import io.renren.dictionary.config.DictionaryProperties;
import io.renren.dictionary.constants.DictOperation;
import io.renren.dictionary.service.IDictService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.dictionary.constants.DictConstant;
import io.renren.modules.sys.dao.SysDictDao;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService, IDictService {

	@Autowired
	private DictionaryProperties dictionaryProperties;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        String type = (String)params.get("type");
        Page<SysDictEntity> page = (Page<SysDictEntity>) this.baseMapper.selectPage(
                new Query<SysDictEntity>(params).getPage(),
                new QueryWrapper<SysDictEntity>()
                    .like(StringUtils.isNotBlank(name),"name", name)
                    .eq(StringUtils.isNotBlank(type), "type", type)
                    .orderByAsc("type")
        );
        return new PageUtils(page);
    }

	@Override
	public List<Map<String, Object>> getAllSysDictEntity() {
		return this.baseMapper.selectMaps(null);
	}

	@Override
	public List<Map<String, Object>> getSysDictEntity(String type) {
		return this.baseMapper.selectMaps(new QueryWrapper<SysDictEntity>().eq(dictionaryProperties.getType(), type));
	}

	@Override
	public Map<String, List<Map<String, Object>>> getSysDictEntityAfterDelete(Long[] ids) {
		// 查询删除的实体
		List<SysDictEntity> sysDictEntityList = this.baseMapper.getDeletedDictEntityByIds(ids);
		// type去重复，重新查询并放入相应的key中
		Map<String, List<Map<String, Object>>> dictMapGroup = sysDictEntityList.stream()
				.map(SysDictEntity::getType)
				.distinct()
				.collect(Collectors.toMap(type -> type,
						type -> this.baseMapper.selectMaps(new QueryWrapper<SysDictEntity>().eq(dictionaryProperties.getType(), type))));
		return dictMapGroup;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@DictionaryCache(operation = DictOperation.INSERT)
    public void insertDict(SysDictEntity sysDictEntity) {
		this.baseMapper.insert(sysDictEntity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@DictionaryCache(operation = DictOperation.INSERT)
    public void updateDict(SysDictEntity sysDictEntity) {
		this.updateById(sysDictEntity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	@DictionaryCache(operation = DictOperation.DELETE)
    public void deleteDict(Long[] ids) {
		this.baseMapper.deleteBatchIds(Arrays.asList(ids));
	}

}
