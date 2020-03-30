package com.drivetuningsh.validator;

import com.drivetuningsh.validator.impl.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "{error.email.not.valid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
