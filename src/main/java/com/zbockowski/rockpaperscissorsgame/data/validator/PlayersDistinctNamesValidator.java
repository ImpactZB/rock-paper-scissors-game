package com.zbockowski.rockpaperscissorsgame.data.validator;

import com.zbockowski.rockpaperscissorsgame.data.dto.PlayerDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersDistinctNamesValidator implements ConstraintValidator<PlayersDistinctNamesConstraint, List<PlayerDto>> {

    @Override
    public boolean isValid(List<PlayerDto> players, ConstraintValidatorContext constraintValidatorContext) {
        if(players == null) {
            return true;
        }

        return players.stream()
                .filter(player -> player.getName() != null)
                .collect(Collectors.groupingBy(PlayerDto::getName, Collectors.toList()))
                .values()
                .stream()
                .noneMatch(set -> set.size() > 1);
    }
}