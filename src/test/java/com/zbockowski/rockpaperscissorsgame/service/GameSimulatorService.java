package com.zbockowski.rockpaperscissorsgame.service;

import com.zbockowski.rockpaperscissorsgame.data.dto.*;
import com.zbockowski.rockpaperscissorsgame.util.RandomShapeGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameSimulatorServiceTest {

    @Mock
    RandomShapeGenerator randomShapeGenerator;

    @InjectMocks
    GameSimulatorService gameSimulatorService;

    @Test
    void judgeGame_shouldCorrectlyJudgeGameBetweenPlayerWithPaperAndPlayerWithRock(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.PAPER), createPlayer("p2", Shape.ROCK));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(1, gameResultDto.getWinners().size());
        PlayerDto winner = gameResultDto.getWinners().stream().findFirst().get();
        assertEquals("p1", winner.getName());
        assertEquals(Shape.PAPER, winner.getShape());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameBetweenPlayerWithPaperAndPlayerWithScissors(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.PAPER), createPlayer("p2", Shape.SCISSORS));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(1, gameResultDto.getWinners().size());
        PlayerDto winner = gameResultDto.getWinners().stream().findFirst().get();
        assertEquals("p2", winner.getName());
        assertEquals(Shape.SCISSORS, winner.getShape());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameBetweenPlayerWithRockAndPlayerWithScissors(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.ROCK), createPlayer("p2", Shape.SCISSORS));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(1, gameResultDto.getWinners().size());
        PlayerDto winner = gameResultDto.getWinners().stream().findFirst().get();
        assertEquals("p1", winner.getName());
        assertEquals(Shape.ROCK, winner.getShape());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameBetweenPlayerWithSameShapeThatIsRock(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.ROCK), createPlayer("p2", Shape.ROCK));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(0, gameResultDto.getWinners().size());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameBetweenPlayerWithSameShapeThatIsScissors(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.SCISSORS), createPlayer("p2", Shape.SCISSORS));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(0, gameResultDto.getWinners().size());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameBetweenPlayerWithSameShapeThatIsPaper(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.PAPER), createPlayer("p2", Shape.PAPER));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(0, gameResultDto.getWinners().size());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameAmongPlayersWithRockPaperAndScissors(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.ROCK),
                createPlayer("p2", Shape.SCISSORS),
                createPlayer("p3", Shape.PAPER));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(0, gameResultDto.getWinners().size());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameAmongPlayersWithRockAndScissors(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.ROCK),
                createPlayer("p2", Shape.SCISSORS),
                createPlayer("p3", Shape.ROCK));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(2, gameResultDto.getWinners().size());
        Optional<PlayerDto> winnerP1Opt = gameResultDto.getWinners().stream().filter(player -> "p1".equals(player.getName())).findFirst();

        assertTrue(winnerP1Opt.isPresent());
        PlayerDto winnerP1 = winnerP1Opt.get();
        assertEquals(Shape.ROCK, winnerP1.getShape());

        Optional<PlayerDto> winnerP3Opt = gameResultDto.getWinners().stream().filter(player -> "p3".equals(player.getName())).findFirst();
        assertTrue(winnerP3Opt.isPresent());
        PlayerDto winnerP3 = winnerP3Opt.get();
        assertEquals(Shape.ROCK, winnerP3.getShape());
    }

    @Test
    void judgeGame_shouldCorrectlyJudgeGameAmongPlayersWithPaperAndScissors(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1", Shape.PAPER),
                createPlayer("p2", Shape.SCISSORS),
                createPlayer("p3", Shape.PAPER),
                createPlayer("p4", Shape.SCISSORS));
        GameDto gameDto = new GameDto();
        gameDto.setPlayers(players);

        //when
        GameResultDto gameResultDto = gameSimulatorService.judgeGame(gameDto);

        //then
        assertEquals(2, gameResultDto.getWinners().size());

        Optional<PlayerDto> winnerP2Opt = gameResultDto.getWinners().stream().filter(player -> "p2".equals(player.getName())).findFirst();
        assertTrue(winnerP2Opt.isPresent());
        PlayerDto winnerP2 = winnerP2Opt.get();
        assertEquals(Shape.SCISSORS, winnerP2.getShape());

        Optional<PlayerDto> winnerP4Opt = gameResultDto.getWinners().stream().filter(player -> "p4".equals(player.getName())).findFirst();
        assertTrue(winnerP4Opt.isPresent());
        PlayerDto winnerP4 = winnerP4Opt.get();
        assertEquals(Shape.SCISSORS, winnerP4.getShape());
    }

    @Test
    void playWithComputer_playerShouldWinWithComputer(){
        //given
        ShapeDto shapeDto = new ShapeDto();
        shapeDto.setShape(Shape.SCISSORS);
        when(randomShapeGenerator.drawShape()).thenReturn(Shape.PAPER);

        //when
        String result = gameSimulatorService.playWithComputer(shapeDto);

        //then
        assertEquals("You win! Computer shape was a PAPER.", result);
    }

    @Test
    void playWithComputer_playerShouldLostWithComputer(){
        //given
        ShapeDto shapeDto = new ShapeDto();
        shapeDto.setShape(Shape.SCISSORS);
        when(randomShapeGenerator.drawShape()).thenReturn(Shape.ROCK);

        //when
        String result = gameSimulatorService.playWithComputer(shapeDto);

        //then
        assertEquals("Unfortunately you lost. Computer shape was a ROCK.", result);
    }

    @Test
    void playWithComputer_noOneShouldWinPlayingWithComputer(){
        //given
        ShapeDto shapeDto = new ShapeDto();
        shapeDto.setShape(Shape.PAPER);
        when(randomShapeGenerator.drawShape()).thenReturn(Shape.PAPER);

        //when
        String result = gameSimulatorService.playWithComputer(shapeDto);

        //then
        assertEquals("No one win. Computer shape was also a PAPER.", result);
    }

    private PlayerDto createPlayer(String name, Shape shape){
        PlayerDto player = new PlayerDto();
        player.setName(name);
        player.setShape(shape);
        return player;
    }
}
