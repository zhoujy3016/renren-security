package io.renren.dictionary.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义数据字典service
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
public class ExtraDictService {

	@Autowired
	private SqlSession sqlSession;

	/**
	 *	查询sql后，将结果集存放到该map
	 */
	private Map<String, Object> extraMap;

	/**
	 *	存放key与sql语句的map
	 */
	private Map<String, String> sqlMap;

	private String statement;

	public Map<String, Object> getExtraMap() {
		return extraMap;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	/**
	 * init方法
	 */
	private void init() {
		System.out.println("配置文件形式加载数据字典bean初始化");
		System.out.println("####extra-dict start:");
		// 循环sqlMap， 将每一个key对应的sql语句进行查询，并放入到extraMap中
		sqlMap.keySet().stream().forEach(key -> collectExtraData(sqlMap, key));
		System.out.println("####extra-dict end:");
	}

	public void addAll(Map<String, String> map) {
		sqlMap.putAll(map);
	}
	
	public ExtraDictService() {
		extraMap = new HashMap<>(10);
		sqlMap = new HashMap<>(10);
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
		return this.sqlSession.selectList(this.statement, sql);
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
