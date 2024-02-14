package com.biscuitka.minesweeper.mapper;

import com.biscuitka.minesweeper.dto.GameInfoResponse;
import com.biscuitka.minesweeper.dto.GameTurnRequest;
import com.biscuitka.minesweeper.dto.NewGameRequest;
import com.biscuitka.minesweeper.model.GameObject;

public class GameMapper {

    public static GameInfoResponse fromGameObjectToResponse(GameObject gameObject) {
        GameInfoResponse response = new GameInfoResponse();
        response.setWidth(gameObject.getWidth());
        response.setHeight(gameObject.getHeight());
        response.setMinesCount(gameObject.getMinesCount());
        response.setField(gameObject.getField());
        response.setCompleted(gameObject.isCompleted());
        response.setGameId(gameObject.getGameId());
        return response;
    }

    public static GameObject fromNewGameRequestToGameObject(NewGameRequest newGameRequest) {
        GameObject gameObject = new GameObject();
        gameObject.setWidth(newGameRequest.getWidth());
        gameObject.setHeight(newGameRequest.getHeight());
        gameObject.setMinesCount(newGameRequest.getMinesCount());
        return gameObject;
    }

    public static GameObject fromGameTurnRequestToGameObj(GameTurnRequest gameTurnRequest) {
        GameObject gameObject = new GameObject();
        gameObject.setGameId(gameTurnRequest.getGameId());
        return gameObject;
    }


}
