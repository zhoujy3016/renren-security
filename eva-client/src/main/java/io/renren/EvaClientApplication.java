package io.renren;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class}) // 引用了common,需要对数据源进行配置，这里exclude掉
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class EvaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvaClientApplication.class, args);
    }
}
