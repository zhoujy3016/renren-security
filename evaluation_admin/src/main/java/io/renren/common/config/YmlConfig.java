package io.renren.common.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="renren")
@Configuration
//@ConditionalOnProperty(name="renren.redis.open", havingValue="true")
public class YmlConfig {
	
	private Map<String, String> extraDict = new HashMap<>();

	public Map<String, String> getExtraDict() {
		return extraDict;
	}

	public void setExtraDict(Map<String, String> extraDict) {
		this.extraDict = extraDict;
	}
	
}
