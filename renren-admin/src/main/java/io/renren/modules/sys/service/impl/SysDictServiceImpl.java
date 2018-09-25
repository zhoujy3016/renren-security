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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService, IDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("name");
        String type = (String)params.get("type");
        Page<SysDictEntity> page = this.selectPage(
                new Query<SysDictEntity>(params).getPage(),
                new EntityWrapper<SysDictEntity>()
                    .like(StringUtils.isNotBlank(name),"name", name)
                    .eq(StringUtils.isNotBlank(type), "type", type)
                    .orderBy("type")
        );
        return new PageUtils(page);
    }

	@Override
	public List<Map<String, Object>> getAllSysDictEntity() {
		EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
		List<Map<String, Object>> sysDictEntityMap = this.baseMapper.selectMaps(ew);
		return sysDictEntityMap;
	}

	@Override
	public List<Map<String, Object>> getSysDictEntity(String type) {
		EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
		ew.eq(DictConstant.DICT_TYPE, type);
		return this.selectMaps(ew);
	}

	@Override
	public List<Map<String, Object>> getSysDictEntityAfterDelete(Long[] ids) {
		List<Map<String, Object>> sysDictEntityMap = this.baseMapper.getSysDictEntityGroupByType(ids);
		// 提取type集合
		List<String> typeList = sysDictEntityMap.stream().map(dictMap -> (String)dictMap.get(DictConstant.DICT_TYPE)).collect(Collectors.toList());
		EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
		// 将删除的type集合为参数，查询更新后的集合
		ew.in(DictConstant.DICT_TYPE, typeList);
		return this.selectMaps(ew);
	}
	@Override
	@DictionaryCache(operation = DictConstant.DictOperation.OP_INSERT)
    public void insertDict(SysDictEntity sysDictEntity) {
		this.insert(sysDictEntity);
	}

	@Override
	@DictionaryCache(operation = DictConstant.DictOperation.OP_UPDATE)
    public void updateDict(SysDictEntity sysDictEntity) {
		this.updateById(sysDictEntity);
	}

	@Override
	@DictionaryCache(operation = DictConstant.DictOperation.OP_DELETE)
    public void deleteDict(Long[] ids) {
		this.deleteBatchIds(Arrays.asList(ids));
	}

}
