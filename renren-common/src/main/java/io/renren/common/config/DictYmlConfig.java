package io.renren.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * 读取自定义数据字典配置文件类
 *
 * @author zhoujunyi
 * @email zhoujunyi-110@163.com
 * @date 2018-07-12 22:00
 */
@ConfigurationProperties(prefix="dictionary")
@Configuration
public class DictYmlConfig {
	// 读取配置文件， 存放key与sql语句的map
	private Map<String, String> extraDict = new HashMap<>();
	
	// mapper.xml中 namespace.id
	private String statement;

	public Map<String, String> getExtraDict() {
		return extraDict;
	}

	public void setExtraDict(Map<String, String> extraDict) {
		this.extraDict = extraDict;
	}
	
	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	/*
	 * 加载sql查询形式的数据字典配置文件
	 */
    @Bean
	@ConditionalOnResource(resources = "config/dictionary-config.yml")
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        // 设置自定义数据字典文件目录
        yaml.setResources(new ClassPathResource("config/dictionary-config.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
