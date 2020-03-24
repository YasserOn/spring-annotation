package top.cyw.component.entity;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.cyw.component.config.MainConfig;

public class IocTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

    /**
     * 往ioc容器中注册组件的方式
     *      1、 包扫描 + @Component注解 [ 我们自己写的类 ]
     *      2、 @Bean + 配置类 [ 引入第三方类 ]
     *      3、@Import
     *          1) 直接在value属性中 Class
     *          2) ImportSelector --> 返回需要导入组件的全类名 字符串数据
     *          3) ImportBeanDefinitionRegistrar --> 手动注册 bean
     */
    @Test
    public void importTest() {
        printBeans(context);
    }

    @Test
    public void conditionTest() {
        String[] type = context.getBeanNamesForType(SMSConfigBean.class);
        System.out.println("==========condition前==========");
//        ronglian
//        aliyun
        printlnStringArray(type);
        System.out.println("==========condition后==========");   //无

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