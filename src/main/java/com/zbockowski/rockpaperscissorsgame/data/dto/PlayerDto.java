package com.zbockowski.rockpaperscissorsgame.data.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class PlayerDto {

    @NotNull(message = "Player name can't be null.")
    private String name;

    @NotNull(message = "Player shape can't be null.")
    private Shape shape;
}
