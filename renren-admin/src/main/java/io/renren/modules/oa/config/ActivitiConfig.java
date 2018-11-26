package io.renren.modules.oa.config;

import io.renren.modules.oa.service.IRuntimeService;
import io.renren.modules.oa.service.ITaskService;
import io.renren.modules.oa.service.impl.MyRuntimeServiceImpl;
import io.renren.modules.oa.service.impl.MyTaskServiceImpl;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    @Autowired
    @Qualifier("firstDataSource")
    private DataSource dataSource;

    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        // 对所有表进行更新，不存在则自动创建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
        // 事务配置？
        return configuration;
    }

    @Bean
    public ITaskService iTaskService() {
        return new MyTaskServiceImpl();
    }

    @Bean
    public IRuntimeService iRuntimeService() {
        return new MyRuntimeServiceImpl();
    }

//    @Bean
//    public ProcessEngine processEngine() {
//        return processEngineConfiguration().buildProcessEngine();
//    }

//    @Bean
//    public SpringProcessEngineConfiguration springProcessEngineConfiguration(
//            PlatformTransactionManager transactionManager,
//            SpringAsyncExecutor springAsyncExecutor) throws IOException {
//
//        return baseSpringProcessEngineConfiguration(
//                activitiDataSource(),
//                transactionManager,
//                springAsyncExecutor);
//    }
}
