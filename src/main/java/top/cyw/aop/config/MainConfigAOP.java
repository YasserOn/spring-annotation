package top.cyw.aop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import top.cyw.aop.aspect.RecordAspect;
import top.cyw.aop.service.CalculatorService;

@Configuration
@ComponentScan(value = "top.cyw.aop")
@EnableAspectJAutoProxy
public class MainConfigAOP {

    @Bean
    public RecordAspect recordAspect(){
        return new RecordAspect();
    }

    @Bean
    public CalculatorService calculatorService(){
        return new CalculatorService();
    }

}

