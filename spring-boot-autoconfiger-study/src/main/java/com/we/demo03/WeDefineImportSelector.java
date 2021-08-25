package com.we.demo03;

import com.we.demo01.MyBatisConfiguration;
import com.we.demo02.RedisConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author we
 * @date 2021-08-25 11:18
 **/
public class WeDefineImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 目的：动态导入bean, 告诉了Spring，两个配置类在哪里
        // TODO 在这里去加载所有的配置类/具体类就行？
        // 思考：这里我是写死的，具体通过某种机制去完成指定路径的配置类的扫描就行？
        // package.class.classname
        return new String[]{RedisConfiguration.class.getName(), MyBatisConfiguration.class.getName()};
    }
}
