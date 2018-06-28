package io.renren.common.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.common.config.DictYmlConfig;
import io.renren.modules.sys.entity.SysDictEntity;


@Service
public class ExtraDictService {
	
	@Autowired
	private DictYmlConfig dictYmlConfig;
	
	@Autowired
	private SqlSession sqlSession;
	
	private Map<String, Object> extraMap;
	
	public void init() {
		System.out.println("配置文件形式加载数据字典bean初始化");
		extraMap = new HashMap<>();
		Map<String, String> sqlMap = dictYmlConfig.getExtraDict();
		for (String keys : sqlMap.keySet()) {
			String sql = sqlMap.get(keys);
			List<SysDictEntity> extraDictList = this.sqlSession.selectList("io.renren.modules.sys.dao.SysDictDao.excuteSelectExtraDictSql", sql);
			extraMap.put(keys, extraDictList);
		}
	}
	
	public ExtraDictService() {
		super();
		
	}
	
	public void destory() {
		System.out.println("配置文件形式加载数据字典bean销毁");
	}

	public Map<String, Object> getExtraMap() {
		return extraMap;
	}

	public void setExtraMap(Map<String, Object> extraMap) {
		this.extraMap = extraMap;
	}

}
