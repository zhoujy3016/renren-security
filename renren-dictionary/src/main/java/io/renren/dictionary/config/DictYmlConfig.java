package io.renren.dictionary.config;

import java.util.Map;

import io.renren.dictionary.conditional.DictYmlCondition;
import io.renren.dictionary.utils.DictConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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

@Configuration("DictYmlConfig")
@Conditional(DictYmlCondition.class)
@ConditionalOnResource(resources=DictConstant.DICT_CONFIG_PATH)
@ConfigurationProperties(prefix=DictConstant.DICT_PREFIX)
@Getter
@Setter
public class DictYmlConfig {
	/** 读取配置文件， 存放key与sql语句的map */
	private Map<String, String> extraDict;
	/** mapper.xml中 namespace.id */
	private String statement;

	/** 加载sql查询形式的数据字典配置文件 */
    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        // 设置自定义数据字典文件目录
        yaml.setResources(new ClassPathResource(DictConstant.DICT_CONFIG_PATH));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
    }
}
