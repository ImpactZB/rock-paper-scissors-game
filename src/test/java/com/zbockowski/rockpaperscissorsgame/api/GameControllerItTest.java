package com.zbockowski.rockpaperscissorsgame.api;

import com.zbockowski.rockpaperscissorsgame.RockPaperScissorsGameApplication;
import com.zbockowski.rockpaperscissorsgame.util.RandomShapeGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {RockPaperScissorsGameApplication.class})
@AutoConfigureMockMvc(print = MockMvcPrint.SYSTEM_OUT, printOnlyOnFailure = false)
class GameControllerItTest {

    @Autowired
    MockMvc mvc;

    @Mock
    RandomShapeGenerator randomShapeGenerator;

    @Test
    @DisplayName("API POST /game/judge - judge game among three players")
    void testJudgeGameAmongThreePlayers() throws Exception {
        //when, then
        mvc.perform(post("/game/judge")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"players\": [{\"name\":\"p1\", \"shape\":\"ROCK\"}, {\"name\":\"p2\", \"shape\":\"SCISSORS\"}, {\"name\":\"p3\", \"shape\":\"ROCK\"}] }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.winners", hasSize(2)))
                .andExpect(jsonPath("$.winners[0].name", is("p1")))
                .andExpect(jsonPath("$.winners[0].shape", is("ROCK")))
                .andExpect(jsonPath("$.winners[1].name", is("p3")))
                .andExpect(jsonPath("$.winners[1].shape", is("ROCK")));
    }

    @Test
    @DisplayName("API POST /game/judge - should discover problem with ambiguous players names")
    void testValidateDuplicatedPlayersNames() throws Exception {
        //when, then
        mvc.perform(post("/game/judge")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"players\": [{\"name\":\"p1\", \"shape\":\"ROCK\"}, {\"name\":\"p2\", \"shape\":\"SCISSORS\"}, {\"name\":\"p1\", \"shape\":\"ROCK\"}] }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("players", is("Players must have unique names.")));
    }

    @Test
    @DisplayName("API POST /game/judge - should discover problem with insufficient players number")
    void testValidateInsufficientPlayersNumber() throws Exception {
        //when, then
        mvc.perform(post("/game/judge")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"players\": [{\"name\":\"p1\", \"shape\":\"ROCK\"}] }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("players", is("At least two players are required to play game.")));
    }

    @Test
    @DisplayName("API POST /game/judge - should discover problem with missing player name")
    void testValidateMissingPlayerName() throws Exception {
        //when, then
        mvc.perform(post("/game/judge")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"players\": [{\"name\":\"p1\", \"shape\":\"ROCK\"}, {\"name\":\"p2\", \"shape\":\"SCISSORS\"}, {\"shape\":\"ROCK\"}] }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"players[2].name\":\"Player name can't be null.\"}"));
    }

    @Test
    @DisplayName("API POST /game/judge - should discover problem with missing player shape")
    void testValidateMissingPlayerShape() throws Exception {
        //when, then
        mvc.perform(post("/game/judge")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"players\": [{\"name\":\"p1\", \"shape\":\"ROCK\"}, {\"name\":\"p2\"}, {\"name\":\"p3\", \"shape\":\"ROCK\"}] }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"players[1].shape\":\"Player shape can't be null.\"}"));
    }

    @Test
    @DisplayName("API POST /game/playWithComputer - player should play with computer")
    void testPlayerPlayingWithComputer() throws Exception {
        //when, then
        mvc.perform(post("/game/playWithComputer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"shape\":\"ROCK\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(anyOf(
                        is("You win! Computer shape was a SCISSORS."),
                        is("Unfortunately you lost. Computer shape was a PAPER."),
                        is("No one win. Computer shape was also a ROCK."))));
    }

    @Test
    @DisplayName("API POST /game/playWithComputer - should discover problem with missing player shape")
    void testValidateMissingPlayerShapePlayingWithComputer() throws Exception {
        //when, then
        mvc.perform(post("/game/playWithComputer")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string("{\"shape\":\"Shape can't be null.\"}"));
    }
}
