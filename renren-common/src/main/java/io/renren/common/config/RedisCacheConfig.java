//package io.renren.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.cache.RedisCacheWriter;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.LinkedHashSet;
//import java.util.List;
//
///**
// * redis缓存配置类
// *
// * @author zhoujunyi
// * @email zhoujunyi-110@163.com
// * @date 2018-08-14 14:00
// */
//@Configuration
//public class RedisCacheConfig {
//    @Bean
//    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
//                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory));
//
//        List<String> cacheNames = new ArrayList<>();
//        // 缓存名称list
//        cacheNames.add("dictionary");
//        builder.initialCacheNames(new LinkedHashSet<>(cacheNames));
//        return builder.cacheDefaults(redisCacheConfiguration).build();
//    }
//}
