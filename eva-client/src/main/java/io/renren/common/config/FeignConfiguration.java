package io.renren.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
//    /**
//     * 将契约改为feign原生的默认契约。这样就可以使用feign自带的注解了。
//     * @return 默认的feign契约
//     */
//    @Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
