package com.zbockowski.rockpaperscissorsgame.api;

import com.zbockowski.rockpaperscissorsgame.data.dto.GameDto;
import com.zbockowski.rockpaperscissorsgame.data.dto.GameResultDto;
import com.zbockowski.rockpaperscissorsgame.data.dto.ShapeDto;
import com.zbockowski.rockpaperscissorsgame.service.GameSimulatorService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = GameController.GAME_ENDPOINT)
public class GameController {

    public static final String GAME_ENDPOINT = "/game";

    private final GameSimulatorService gameSimulatorService;

    @PostMapping(path = "/judge", produces = "application/json")
    @ApiOperation("Judge a game")
    public ResponseEntity<GameResultDto> judgeGame(@RequestBody @Valid GameDto gameDto){
        return new ResponseEntity<>(gameSimulatorService.judgeGame(gameDto), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(path = "/playWithComputer", produces = "application/json")
    @ApiOperation("Play a game with a computer")
    public ResponseEntity<String> playWithComputer(@RequestBody @Valid ShapeDto shapeDto){
        return new ResponseEntity<>(gameSimulatorService.playWithComputer(shapeDto), new HttpHeaders(), HttpStatus.OK);
    }
}
