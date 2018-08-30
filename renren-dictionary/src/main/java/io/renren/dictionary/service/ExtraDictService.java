package io.renren.dictionary.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.renren.dictionary.config.DictYmlConfig;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自定义数据字典service
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@Service
public class ExtraDictService {
	
	@Autowired(required = false)
	private DictYmlConfig dictYmlConfig;
	
	@Autowired
	private SqlSession sqlSession;
	
	private Map<String, Object> extraMap;

	public Map<String, Object> getExtraMap() {
		return extraMap;
	}

	public void setExtraMap(Map<String, Object> extraMap) {
		this.extraMap = extraMap;
	}

	/**
	 * init方法
	 */
	private void init() {
		System.out.println("配置文件形式加载数据字典bean初始化");
		if(dictYmlConfig != null) {
			extraMap = new HashMap<>(10);
			Map<String, String> sqlMap = dictYmlConfig.getExtraDict();
			System.out.println("####extra-dict start:");
			for (String keys : sqlMap.keySet()) {
				String sql = sqlMap.get(keys);
				List<?> extraDictList = this.excuteQuery(sql);
				extraMap.put(keys, extraDictList);
			}
			System.out.println("####extra-dict end:");
		}
	}
	
	public ExtraDictService() {
		super();
		
	}

	/**
	 * destroy
	 */
	private void destory() {
		System.out.println("配置文件形式加载数据字典bean销毁");
	}

	/**
	 * 执行sql查询
	 * @param sql
	 * @return
	 */
	public List<?> excuteQuery(String sql) {
		return this.sqlSession.selectList(dictYmlConfig.getStatement(), sql);
	}
}