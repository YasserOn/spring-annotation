package top.cyw.aop.service;

import org.springframework.stereotype.Service;
import top.cyw.aop.annotation.Record;


/**
 * 需求: 想在div方法前后 加上自己想要的方法
 */
public class CalculatorService {

    @Record
    public int div(int num1, int num2) {
        System.out.println("执行除法");
        return num1 / num2;
    }

}
