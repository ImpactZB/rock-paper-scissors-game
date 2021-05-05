package com.zbockowski.rockpaperscissorsgame.data.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class PlayerCountValidator implements ConstraintValidator<PlayerCountConstraint, List> {

    @Override
    public boolean isValid(List players, ConstraintValidatorContext constraintValidatorContext) {
        return players != null && players.size() > 1;
    }
}
