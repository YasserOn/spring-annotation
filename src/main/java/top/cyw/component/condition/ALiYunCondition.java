package top.cyw.component.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

public class ALiYunCondition implements Condition {

    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        ClassLoader loader = context.getClassLoader();
        BeanDefinitionRegistry registry = context.getRegistry();
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("aliyun.enable");
        return !StringUtils.isEmpty(property) && "true".equals(property);
    }
}
