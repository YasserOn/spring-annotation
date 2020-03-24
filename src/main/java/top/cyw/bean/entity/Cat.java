package top.cyw.bean.entity;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Data
public class Cat {
    private String name;

    public Cat() {
        this.name = "大黄猫";
        System.out.println("Cat construct");
    }

    @PostConstruct
    public void init() {
        this.name = "小黄猫";
        System.out.println("Cat PostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Cat PreDestroy");
    }

    public Cat(String name) {
        this.name = name;
    }
}
