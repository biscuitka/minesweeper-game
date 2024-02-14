package com.biscuitka.minesweeper.service;

import com.biscuitka.minesweeper.dto.GameInfoResponse;
import com.biscuitka.minesweeper.dto.GameTurnRequest;
import com.biscuitka.minesweeper.dto.NewGameRequest;

public interface GameService {
GameInfoResponse createNewGame(NewGameRequest newGameRequest);
GameInfoResponse makeGameMove(GameTurnRequest gameTurnRequest);
}
