package com.carloan.userprofile.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailContent {
    String message() default "Please enter a valid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
