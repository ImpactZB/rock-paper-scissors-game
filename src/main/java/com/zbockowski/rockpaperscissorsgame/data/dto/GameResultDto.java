package com.zbockowski.rockpaperscissorsgame.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameResultDto {

    private List<PlayerDto> winners;
}
