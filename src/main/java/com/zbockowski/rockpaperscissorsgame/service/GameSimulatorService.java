package com.zbockowski.rockpaperscissorsgame.service;

import com.zbockowski.rockpaperscissorsgame.data.dto.*;
import com.zbockowski.rockpaperscissorsgame.util.RandomShapeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class GameSimulatorService {

    private final RandomShapeGenerator randomShapeGenerator;

    public GameResultDto judgeGame(GameDto gameDto){
        List<PlayerDto> players = gameDto.getPlayers();
        List<PlayerDto> winners = players.stream()
                .filter(player -> noOneHasStrongerShape(player, findPlayerOpponents(player, players)))
                .collect(Collectors.toList());

        if(winners.size() == players.size()){
            winners.clear();
        }

        return GameResultDto.builder().winners(winners).build();
    }

    private boolean noOneHasStrongerShape(PlayerDto player, Set<PlayerDto> opponents){
        return opponents.stream().noneMatch(opponent -> player.getShape().getStrongerShape().equals(opponent.getShape()));
    }

    private Set<PlayerDto> findPlayerOpponents(PlayerDto player, List<PlayerDto> players){
        return players.stream()
                .filter(opponent -> !opponent.getName().equals(player.getName()))
                .collect(Collectors.toSet());
    }

    public String playWithComputer(ShapeDto shapeDto){
        Shape computerShape = randomShapeGenerator.drawShape();
        if(computerShape.equals(shapeDto.getShape().getStrongerShape())){
            return String.format("Unfortunately you lost. Computer shape was a %s.", computerShape.name());
        } else if (computerShape.getStrongerShape().equals(shapeDto.getShape())) {
            return String.format("You win! Computer shape was a %s.", computerShape.name());
        }
        return String.format("No one win. Computer shape was also a %s.", computerShape.name());
    }
}