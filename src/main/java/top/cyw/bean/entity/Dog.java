package top.cyw.bean.entity;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Dog {
    private String name;

    public Dog() {
        this.name = "舔狗";
        System.out.println("Dog construct");
    }

    @PostConstruct
    public void init() {
        this.name = "小黑狗";
        System.out.println("Dog PostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Dog PreDestroy");
    }

    public Dog(String name) {
        this.name = name;
    }
}
