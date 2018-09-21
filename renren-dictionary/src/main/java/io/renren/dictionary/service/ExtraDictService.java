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
			// 循环sqlMap， 将每一个key对应的sql语句进行查询，并放入到extraMap中
			sqlMap.keySet().stream().forEach(key -> collectExtraData(sqlMap, key));
			System.out.println("####extra-dict end:");
		}
	}
	
	public ExtraDictService() {
		super();
		
	}

	/**
	 * destroy
	 */
	private void destroy() {
		System.out.println("配置文件形式加载数据字典bean销毁");
	}

	/**
	 * 执行sql查询
	 * @param sql
	 * @return
	 */
	public List<?> executeQuery(String sql) {
		return this.sqlSession.selectList(dictYmlConfig.getStatement(), sql);
	}

	/**
	 * 额外数据字典查询，并放入到extraMap中
	 * @param sqlMap
	 * @param key
	 */
	private void collectExtraData(Map<String, String> sqlMap, String key) {
		String sql = sqlMap.get(key);
		List<?> extraDictList = this.executeQuery(sql);
		extraMap.put(key, extraDictList);
	}
}
