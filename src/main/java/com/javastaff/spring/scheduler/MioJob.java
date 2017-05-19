package com.javastaff.spring.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class MioJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(MioJob.class);
	
	@Value("${quartz.miojob.intervallo}")
    private long intervallo;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		LOG.info("Sono un job lanciato con quartz ogni {} millisecondi", intervallo);
	}
	
	@Bean(name = "jobDetail")
    public JobDetailFactoryBean creaMioJobDetail() {
        return QuartzConfiguration.createJobDetail(this.getClass());
    }

    @Bean(name = "jobTrigger")
    public SimpleTriggerFactoryBean creaMioJobTrigger(@Qualifier("jobDetail") JobDetail jobDetail) {
    	return QuartzConfiguration.createTrigger(jobDetail,intervallo);
    }
}