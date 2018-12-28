//package io.renren.common.config;
//
//import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
//
//@ConditionalOnProperty(prefix = "renren", value = "cluster", havingValue = "true")
//@EnableRedisHttpSession
//public class SpringSessionConfig {
//    /**
//     * 集群环境，session交给spring-session管理
//     */
//    @Bean
//    public ServletContainerSessionManager servletContainerSessionManager(@Value("${renren.globalSessionTimeout}") int globalSessionTimeout,
//                                                                         RedisHttpSessionConfiguration redisHttpSessionConfiguration) {
//        // 设置超时时间
//        redisHttpSessionConfiguration.setMaxInactiveIntervalInSeconds(globalSessionTimeout);
//        return new ServletContainerSessionManager();
//    }
//}