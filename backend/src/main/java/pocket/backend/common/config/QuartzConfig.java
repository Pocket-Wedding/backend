package pocket.backend.common.config;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    private final AutoWiringSpringBeanJobFactory autoWiringSpringBeanJobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(autoWiringSpringBeanJobFactory);
        schedulerFactoryBean.setTriggers(jobTrigger(jobDetail()));
        schedulerFactoryBean.setJobDetails(jobDetail());
        schedulerFactoryBean.setAutoStartup(true);
        return schedulerFactoryBean;
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(ExampleJob.class)
                .storeDurably()
                .withIdentity("Quartz_Job_Detail")
                .withDescription("Invoke Sample Job service...")
                .build();
    }

    @Bean
    public Trigger jobTrigger(JobDetail job) {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("Quartz_Job_Trigger")
                .withDescription("Sample trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
