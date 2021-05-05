package com.zbockowski.rockpaperscissorsgame.data.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PlayerCountValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PlayerCountConstraint {

    String message() default "At least two players are required to play game.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
