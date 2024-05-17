package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {StateValidation.class}) // 指定校验器
public @interface State {
    // 校验不通过时的提示信息
    String message() default "state的参数不正确";

    // 指定分组
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
