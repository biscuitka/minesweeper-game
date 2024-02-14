package com.biscuitka.minesweeper.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameInfoResponse {
    String gameId;
    Integer width;
    Integer height;
    Integer minesCount;
    boolean completed;
    String[][] field;
}
