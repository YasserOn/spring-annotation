package top.cyw.component.importDemo.importSelector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑返回需要导入的组件
public class MyImportSelector implements ImportSelector {

    //返回的为  导入组件的全类名 字符串数组
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //方法不能返回 null 值
        //能返回一个空数组
        return new String[]{"top.cyw.component.entity.importDemo.selector.SelectorGreen","top.cyw.component.entity.importDemo.selector.SelectorYellow"};
    }


}
