package com.zbockowski.rockpaperscissorsgame.data.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlayersDistinctNamesValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlayersDistinctNamesConstraint {

    String message() default "Players must have unique names.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
