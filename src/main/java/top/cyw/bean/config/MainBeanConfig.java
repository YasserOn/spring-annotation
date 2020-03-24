package top.cyw.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.cyw.bean.entity.Cat;
import top.cyw.bean.entity.Dog;

@Configuration
@ComponentScan(value = "top.cyw.bean")
public class MainBeanConfig {

    @Bean
    public Cat cat(){
        return new Cat();
    }

    @Bean
    public Dog dog(){
        return new Dog();
    }

}
