package top.cyw.component;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.cyw.component.config.MainConfig;
import top.cyw.component.entity.Person;

public class MainClass {


    public static void main(String[] args) {
//        getBeanForXML();
        getBeanForAnnotation();
    }

    private static void getBeanForAnnotation(){
        // 读取配置类 --> 相当于之前是从xml中获取bean的信息  现在变成了 从java的配置类中获取bean的信息
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Person config = getBeans(context, Person.class, "person-config");
        System.out.println("config:"+config);
        System.out.println("=======获取bean的定义信息=======");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        printlnStringArray(beanDefinitionNames);
        System.out.println("=========getBeanDefinition=========");
        System.out.println(context.getBeanDefinition("person-config"));
        System.out.println("===================================");
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        printlnStringArray(beanDefinitionNames);

    }

    private static void getBeanForXML(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person xml = getBeans(context, Person.class, "person-xml");
        System.out.println("xml:"+xml);
    }

    private static <T> T getBeans(ApplicationContext context, Class<T> bean, String beanName) {
        T target = (T) context.getBean(beanName);
        return target;
    }

    private static void printlnStringArray(String[] beanDefinitionNames) {
        for (String b : beanDefinitionNames) {
            System.out.println(b);
        }
    }
}
