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

import io.renren.common.component.DictComponent;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysDictDao;
import io.renren.modules.sys.entity.SysDictEntity;
import io.renren.modules.sys.service.SysDictService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDictEntity> implements SysDictService {

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
	public List<SysDictEntity> getSysDictEntity(String type) {
		EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
		ew.eq("type", type);
		List<SysDictEntity> sysDictEntityList = this.selectList(ew);
		return sysDictEntityList;
	}

	@Override
	public List<SysDictEntity> getSysDictEntityGroupByType() {
		EntityWrapper<SysDictEntity> ew = new EntityWrapper<>();
		ew.groupBy("type");
		List<SysDictEntity> sysDictEntityList = this.selectList(ew);
		return sysDictEntityList;
	}

	@Override
	public List<SysDictEntity> getSysDictEntityGroupByType(Long[] ids) {
		List<SysDictEntity> sysDictEntityList = this.baseMapper.getSysDictEntityGroupByType(ids);
		return sysDictEntityList;
	}

	@Override
	public void insertDict(SysDictEntity sysDictEntity) {
		this.insert(sysDictEntity);
        String type = sysDictEntity.getType();
        // 更新数据字典缓存
        DictComponent.reloadDictCacheData(type, this.getSysDictEntity(type));
	}

	@Override
	public void updateDict(SysDictEntity sysDictEntity) {
        this.updateById(sysDictEntity);
        String type = sysDictEntity.getType();
        // 更新数据字典缓存
        DictComponent.reloadDictCacheData(type, this.getSysDictEntity(type));
	}

	@Override
	public void deleteDict(Long[] ids) {
        this.deleteBatchIds(Arrays.asList(ids));
        // 更新数据字典
        DictComponent.reloadDictCacheData(ids);
	}

}
