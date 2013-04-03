package com.supinfo.rmt.validator.annotation;

import com.supinfo.rmt.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Ahmad
 * @version $Id$
 */
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface CheckPassword {
    String message() default "{constraints.custom.password.checkPassword}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
