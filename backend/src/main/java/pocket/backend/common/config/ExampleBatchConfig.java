package pocket.backend.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ExampleBatchConfig {

    @Bean(name = "exampleJob1")
    public Job exampleJob1(JobRepository jobRepository, Step exampleStep1, Step exampleStep2) {
        return new JobBuilder("exampleJob1", jobRepository)
                .start(exampleStep1)
                .next(exampleStep2)
                .build();
    }

    @Bean(name = "exampleStep1")
    public Step exampleStep1(JobRepository jobRepository, Tasklet exampleTasklet1, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("exampleStep1", jobRepository)
                .tasklet(exampleTasklet1, platformTransactionManager)
                .build();
    }

    @Bean(name = "exampleStep2")
    public Step exampleStep2(JobRepository jobRepository, Tasklet exampleTasklet2, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("exampleStep2", jobRepository)
                .tasklet(exampleTasklet2, platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet exampleTasklet1() {
        return ((contribution, chunkContext) -> {
            log.info("ExampleTasklet1 executed...");
            return RepeatStatus.FINISHED;
        });
    }

    @Bean
    public Tasklet exampleTasklet2() {
        return ((contribution, chunkContext) -> {
            log.info("ExampleTasklet2 executed...");
            return RepeatStatus.FINISHED;
        });
    }

}
