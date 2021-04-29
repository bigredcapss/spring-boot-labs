package com.we.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author we
 * @date 2021-04-29 18:24
 **/
public class WeImportSelector implements ImportSelector {
    /**
     *  动态获取IoC要加载的类型
     * @param annotationMetadata 注解的元数据
     * @return IoC要加载的类型的全路径的数组
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        // 根据不同的业务逻辑 实现动态添加IoC加载的类型
        /*if (){

        }*/
        return new String[]{LoggerService.class.getName(),CacheService.class.getName()};
    }
}
