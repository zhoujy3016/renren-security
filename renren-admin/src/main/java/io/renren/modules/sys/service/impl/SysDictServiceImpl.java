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
import io.renren.dictionary.service.IDictService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.dictionary.utils.DictConstant;
import io.renren.modules.sys.dao.SysDictDao;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService, IDictService {

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
		return this.baseMapper.selectMaps(new QueryWrapper<>());
	}

	@Override
	public List<Map<String, Object>> getSysDictEntity(String type) {
		return this.baseMapper.selectMaps(new QueryWrapper<SysDictEntity>().eq(DictConstant.DICT_TYPE, type));
	}

	@Override
	public Map<String, List<Map<String, Object>>> getSysDictEntityAfterDelete(Long[] ids) {
		// 查询删除的实体所包含的type
		List<SysDictEntity> sysDictEntityList = this.baseMapper.getSysDictEntityGroupByType(ids);
		Map<String, List<Map<String, Object>>> dictMapGroup = new HashMap<>(5);
		// 通过type重新查询并放入相应的key中
		sysDictEntityList.stream()
				.map(SysDictEntity::getType)
				.forEach(type -> dictMapGroup.put(type, this.baseMapper.selectMaps(new QueryWrapper<SysDictEntity>().eq("type", type))));
		return dictMapGroup;
	}

	@Override
	@DictionaryCache(operation = DictConstant.DictOperation.OP_INSERT)
    public void insertDict(SysDictEntity sysDictEntity) {
		this.baseMapper.insert(sysDictEntity);
	}

	@Override
	@DictionaryCache(operation = DictConstant.DictOperation.OP_UPDATE)
    public void updateDict(SysDictEntity sysDictEntity) {
		this.updateById(sysDictEntity);
	}

	@Override
	@DictionaryCache(operation = DictConstant.DictOperation.OP_DELETE)
    public void deleteDict(Long[] ids) {
		this.baseMapper.deleteBatchIds(Arrays.asList(ids));
	}

}
