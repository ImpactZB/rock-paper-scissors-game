package com.zbockowski.rockpaperscissorsgame.data.validator;

import com.zbockowski.rockpaperscissorsgame.data.dto.PlayerDto;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCountValidatorTest {

    PlayerCountValidator playerCountValidator = new PlayerCountValidator();

    @Test
    void isValid_shouldValidateCorrectlyValidPlayerList(){
        //when, then
        assertTrue(playerCountValidator.isValid(List.of(new PlayerDto(), new PlayerDto()), null));
        assertTrue(playerCountValidator.isValid(List.of(new PlayerDto(), new PlayerDto(), new PlayerDto()), null));
    }

    @Test
    void isValid_shouldValidateCorrectlyInvalidPlayerList(){
        //when, then
        assertFalse(playerCountValidator.isValid(List.of(), null));
        assertFalse(playerCountValidator.isValid(List.of(new PlayerDto()), null));
    }
}
