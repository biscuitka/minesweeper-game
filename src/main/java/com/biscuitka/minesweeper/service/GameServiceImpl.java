package com.biscuitka.minesweeper.service;

import com.biscuitka.minesweeper.dto.GameInfoResponse;
import com.biscuitka.minesweeper.dto.GameTurnRequest;
import com.biscuitka.minesweeper.dto.NewGameRequest;
import com.biscuitka.minesweeper.mapper.GameMapper;
import com.biscuitka.minesweeper.model.GameObject;
import com.biscuitka.minesweeper.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final FieldRepository fieldRepository;

    @Override
    public GameInfoResponse createNewGame(NewGameRequest newGameRequest) {
        GameObject gameObject = GameMapper.fromNewGameRequestToGameObject(newGameRequest);
        return GameMapper.fromGameObjectToResponse(fieldRepository.createGame(gameObject));
    }

    @Override
    public GameInfoResponse makeGameMove(GameTurnRequest gameTurnRequest) {
        GameObject gameObject = GameMapper.fromGameTurnRequestToGameObj(gameTurnRequest);
        int row = gameTurnRequest.getRow();
        int col = gameTurnRequest.getCol();
        return GameMapper.fromGameObjectToResponse(fieldRepository.makeGameMove(gameObject, row, col));
    }
}
