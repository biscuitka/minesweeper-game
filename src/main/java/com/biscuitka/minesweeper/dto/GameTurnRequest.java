package com.biscuitka.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameTurnRequest {
    @JsonProperty("game_id")
    String gameId;
    Integer col;
    Integer row;
}
