package com.we.demo04;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * @author we
 * @date 2021-08-25 11:41
 **/
public class CustomerCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //TODO 业务逻辑判断
        if(1==2){
            return true;
        }else{
            return false;
        }
    }
}
