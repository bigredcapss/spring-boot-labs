package com.we.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author we
 * @date 2021-04-29 18:24
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
//@Import(WeImportSelector.class)
@Import(WeImportBeanDefinition.class)
public @interface EnableWeImport {
}
