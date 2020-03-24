package top.cyw.component.entity;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.cyw.bean.config.MainBeanConfig;
import top.cyw.component.config.MainConfig;

public class BeanTest {



    @Test
    public void test01(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeanConfig.class);
        System.out.println("容器创建完成");

        //关闭容器
        context.close();
        System.out.println("关闭容器");
    }

    private static void printBeans(AnnotationConfigApplicationContext context){
        String[] definitionNames = context.getBeanDefinitionNames();
        printlnStringArray(definitionNames);
    }

    private static void printlnStringArray(String[] beanDefinitionNames) {
        for (String b : beanDefinitionNames) {
            System.out.println(b);
        }
    }
}