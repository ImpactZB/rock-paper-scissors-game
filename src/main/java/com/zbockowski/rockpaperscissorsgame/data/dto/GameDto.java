package com.zbockowski.rockpaperscissorsgame.data.dto;

import com.zbockowski.rockpaperscissorsgame.data.validator.PlayerCountConstraint;
import com.zbockowski.rockpaperscissorsgame.data.validator.PlayersDistinctNamesConstraint;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class GameDto {

    @Valid
    @PlayerCountConstraint
    @PlayersDistinctNamesConstraint
    private List<PlayerDto> players;
}
