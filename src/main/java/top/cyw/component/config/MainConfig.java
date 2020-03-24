package top.cyw.component.config;

import org.springframework.context.annotation.*;
import top.cyw.component.condition.ALiYunCondition;
import top.cyw.component.condition.RongLianCondition;
import top.cyw.component.entity.importDemo.Color;
import top.cyw.component.entity.Person;
import top.cyw.component.entity.importDemo.Red;
import top.cyw.component.entity.SMSConfigBean;
import top.cyw.component.importDemo.ImportBeanDefinitionRegistrar.MyImportBeanDefinitionRegistrar;
import top.cyw.component.importDemo.importSelector.MyImportSelector;

@Import(value = {Color.class, Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
//-->top.cyw.component.entity.Color  top.cyw.component.entity.Red
@Configuration
public class MainConfig {

    @Bean("person-config")
    public Person person() {
        return new Person(19, "spring-config");
    }

    @Bean("ronglian")
    @Conditional(value = {ALiYunCondition.class})
    public SMSConfigBean ronglian() {
        return new SMSConfigBean("ronglian");
    }

    @Bean("aliyun")
    @Conditional(value = {RongLianCondition.class})
    public SMSConfigBean aliyun() {
        return new SMSConfigBean("aliyun");
    }
}
