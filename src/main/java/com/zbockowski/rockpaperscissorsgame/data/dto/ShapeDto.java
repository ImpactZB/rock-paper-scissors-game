package com.zbockowski.rockpaperscissorsgame.data.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShapeDto {

    @NotNull(message = "Shape can't be null.")
    private Shape shape;
}
