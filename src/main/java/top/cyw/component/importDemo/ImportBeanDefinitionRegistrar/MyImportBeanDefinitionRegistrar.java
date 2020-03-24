package top.cyw.component.importDemo.ImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import top.cyw.component.entity.importDemo.beanDefinitionRegistrar.RainBow;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * @param importingClassMetadata 当前类的注解信息
     * @param registry               Bean定义的注册类
     *                               把所需要导入的组件添加到 ioc 容器中
     *                               registry.registerBeanDefinition 手动注册组件
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean existSelectorGreen = registry.containsBeanDefinition("top.cyw.component.entity.importDemo.selector.SelectorGreen");
        boolean existSelectorYellow = registry.containsBeanDefinition("top.cyw.component.entity.importDemo.selector.SelectorYellow");
        if (existSelectorGreen && existSelectorYellow) {
            // new RootBeanDefinition
            RootBeanDefinition rainBow = new RootBeanDefinition(RainBow.class);
            //param[0] --> 组件的id  param[1] --> 组件的注册信息
            registry.registerBeanDefinition("rainbow", rainBow);
        }
    }
}
