package com.we.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author we
 * @date 2021-04-29 18:24
 **/
public class WeImportBeanDefinition implements ImportBeanDefinitionRegistrar {
    /**
     * 提供了一个beanDefinition的注册器，我直接把需要IoC加载的类型注册到容器中去
     * @param annotationMetadata
     * @param beanDefinitionRegistry beanDefinition的注册器
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 将我们需要添加的类型统一封装为RootBeanDefinition对象
        RootBeanDefinition cache = new RootBeanDefinition(CacheService.class);
        beanDefinitionRegistry.registerBeanDefinition("cache",cache);
        RootBeanDefinition logger = new RootBeanDefinition(LoggerService.class);
        beanDefinitionRegistry.registerBeanDefinition("logger",logger);
    }
}
