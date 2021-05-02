package com.we.auth.server.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * 参数验证工具
 * @author we
 * @date 2021-05-02 10:54
 **/
public class ValidatorUtil {

    /**
     * 统一处理加注解后校验的结果
     * @param result
     * @return
     */
    public static String checkResult(BindingResult result){
        StringBuilder sb=new StringBuilder("");

        if (result!=null && result.hasErrors()){
            List<ObjectError> errors=result.getAllErrors();
            /*for (ObjectError error:errors){
                sb.append(error.getDefaultMessage()).append("\n");
            }*/
            // Stream写法
            errors.forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        }
        return sb.toString();
    }
}
