package pocket.backend;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pocket.backend.common.config.AutoWiringSpringBeanJobFactory;

@SpringBootApplication
@EnableJpaAuditing
@EnableTransactionManagement
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public AutoWiringSpringBeanJobFactory jobFactory(AutowireCapableBeanFactory beanFactory) {
        return new AutoWiringSpringBeanJobFactory(beanFactory);
    }

}
