package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 继承接口的第一个参数是校验生效的接口，第二个参数是校验生效的参数类型
public class StateValidation implements ConstraintValidator<State, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则
        // s为校验数据
        if (s == null || s.isEmpty())
            return false;
        return s.equals("已发布") || s.equals("草稿");
    }
}
