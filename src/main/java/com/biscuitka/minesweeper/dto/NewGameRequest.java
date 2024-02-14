package com.biscuitka.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewGameRequest {
    @Min(2)
    @Max(30)
    Integer width;
    @Min(2)
    @Max(30)
    Integer height;
    @Min(1)
    @JsonProperty("mines_count")
    Integer minesCount;

    @AssertTrue(message = "Количество мин не может покрывать все поле, " +
            "всегда должна быть хотя бы одна свободная ячейка")
    boolean isMaxMines() {
        return minesCount <= (width * height - 1);
    }
}
