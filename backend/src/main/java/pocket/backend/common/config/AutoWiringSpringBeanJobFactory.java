package pocket.backend.common.config;

import lombok.RequiredArgsConstructor;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoWiringSpringBeanJobFactory extends SpringBeanJobFactory {

    private final AutowireCapableBeanFactory autoWireCapableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        autoWireCapableBeanFactory.autowireBean(job);
        return job;
    }
}
