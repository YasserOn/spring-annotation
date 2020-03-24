package top.cyw.bean.PostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
@Component
public class MyPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("my 后置处理器BeforeInitialization -->" +bean +" name:"+ beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("my 后置处理器AfterInitialization -->" +bean +" name:"+ beanName);
        return bean;
    }
}
