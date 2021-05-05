package com.zbockowski.rockpaperscissorsgame.data.validator;

import com.zbockowski.rockpaperscissorsgame.data.dto.PlayerDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayersDistinctNamesValidatorTest {

    PlayersDistinctNamesValidator playersDistinctNamesValidator = new PlayersDistinctNamesValidator();

    @Test
    void isValid_shouldValidateCorrectlyValidPlayerList(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1"), createPlayer("p2"));

        //when, then
        assertTrue(playersDistinctNamesValidator.isValid(players, null));
    }

    @Test
    void isValid_shouldValidateCorrectlyInvalidPlayerList(){
        //given
        List<PlayerDto> players = List.of(createPlayer("p1"), createPlayer("p2"), createPlayer("p1"));

        //when, then
        assertFalse(playersDistinctNamesValidator.isValid(players, null));
    }

    private PlayerDto createPlayer(String name){
        PlayerDto player = new PlayerDto();
        player.setName(name);
        return player;
    }
}
