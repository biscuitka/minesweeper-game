package com.biscuitka.minesweeper.controller;

import com.biscuitka.minesweeper.dto.GameInfoResponse;
import com.biscuitka.minesweeper.dto.GameTurnRequest;
import com.biscuitka.minesweeper.dto.NewGameRequest;
import com.biscuitka.minesweeper.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Validated
@RequestMapping(path = "/game")
@RequiredArgsConstructor
public class MinesweeperController {
     private final GameService gameService;

    @PostMapping("/new")
    public GameInfoResponse newGame(@Valid @RequestBody NewGameRequest newGameRequest) {
        log.info("Создание новой игры: {}", newGameRequest);
        return gameService.createNewGame(newGameRequest);
    }

    @PostMapping("/turn")
    public GameInfoResponse makeGameMove(@RequestBody GameTurnRequest gameTurnRequest){
        log.info("Сделан игровой ход: {}", gameTurnRequest);
        return gameService.makeGameMove(gameTurnRequest);
    }
}
