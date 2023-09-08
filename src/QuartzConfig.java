package com.africinvest.erp.config;

import com.africinvest.erp.service.impl.quartz.EmailResendJob;
import com.africinvest.erp.service.impl.quartz.QuartzSecondJob;
import lombok.AllArgsConstructor;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@AllArgsConstructor
public class QuartzConfig {

    final ApplicationContext applicationContext;
    @Bean
    SpringBeanJobFactory createSpringBeanJobFactory() {

        return new SpringBeanJobFactory() {

            @Override
            protected Object createJobInstance
                    (final TriggerFiredBundle bundle) throws Exception {

                final Object job = super.createJobInstance(bundle);

                applicationContext
                        .getAutowireCapableBeanFactory()
                        .autowireBean(job);

                return job;
            }
        };
    }
    @Bean
    public SchedulerFactoryBean createSchedulerFactory(SpringBeanJobFactory springBeanJobFactory, Trigger trigger2,Trigger trigger1,DataSource quartzDataSource) {

        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
        schedulerFactory.setTriggers(trigger2,trigger1);
        springBeanJobFactory.setApplicationContext(applicationContext);
        schedulerFactory.setJobFactory(springBeanJobFactory);
        schedulerFactory.setDataSource(quartzDataSource);
        return schedulerFactory;
    }
    /**
     * job detail config
     * @return jobDetail2
     */
    @Bean(name = "jobDetail2")
    public JobDetailFactoryBean createJobDetailFactoryBean() {

        JobDetailFactoryBean jobDetailFactory
                = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(QuartzSecondJob.class);

        return jobDetailFactory;
    }
    /***
     * job trigger config
     * @param jobDetail1
     * @return
     */
    @Bean("trigger1")
    public SimpleTriggerFactoryBean createSimpleTriggerFactoryBean(JobDetail jobDetail1) {
        SimpleTriggerFactoryBean simpleTriggerFactory = new SimpleTriggerFactoryBean();
        simpleTriggerFactory.setJobDetail(jobDetail1);
        simpleTriggerFactory.setStartDelay(0);
        simpleTriggerFactory.setRepeatInterval(50000);
        return simpleTriggerFactory;
    }
    @Bean("trigger2")
    public SimpleTriggerFactoryBean createSimpleTriggerFactoryBean1(JobDetail jobDetail2) {
        SimpleTriggerFactoryBean simpleTriggerFactory = new SimpleTriggerFactoryBean();
        simpleTriggerFactory.setJobDetail(jobDetail2);
        simpleTriggerFactory.setStartDelay(0);
        simpleTriggerFactory.setRepeatInterval(50000);
        return simpleTriggerFactory;
    }
    @Bean(name = "jobDetail1")
    public JobDetailFactoryBean createJobDetailFactoryBean2() {

        JobDetailFactoryBean jobDetailFactory
                = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(EmailResendJob.class);
        return jobDetailFactory;
    }
}

