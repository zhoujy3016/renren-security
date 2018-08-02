package io.renren.common.utils;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EurekaServerUtils {

    @Autowired
    private  DiscoveryClient discoveryClient;

    public InstanceInfo getServerInstance() {
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("eva-admin");
        if(serviceInstanceList != null && serviceInstanceList.size() > 0) {
            EurekaDiscoveryClient.EurekaServiceInstance instance = (EurekaDiscoveryClient.EurekaServiceInstance) serviceInstanceList.get(0);
            return instance.getInstanceInfo();
        } else {
            return null;
        }
    }
}
