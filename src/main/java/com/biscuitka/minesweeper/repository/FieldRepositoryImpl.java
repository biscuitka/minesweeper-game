package com.biscuitka.minesweeper.repository;

import com.biscuitka.minesweeper.error.exception.BadRequestException;
import com.biscuitka.minesweeper.model.GameObject;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.util.UUID.randomUUID;

@Repository
public class FieldRepositoryImpl implements FieldRepository {
    private boolean[][] mineLocations;
    private boolean[][] revealedCells;
    private String[][] gameField;
    private final Map<String, GameObject> games = new HashMap<>();

    @Override
    public GameObject createGame(GameObject gameObject) {
        int width = gameObject.getWidth();
        int height = gameObject.getHeight();
        int minesCount = gameObject.getMinesCount();
        gameObject.setGameId(randomUUID().toString());
        gameObject.setCompleted(false);
        mineLocations = new boolean[width][height];
        revealedCells = new boolean[width][height];
        gameField = new String[width][height];

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                gameField[row][col] = " ";
            }
        }

        setMinesOnField(width, height, minesCount);

        gameObject.setField(gameField);
        games.put(gameObject.getGameId(), gameObject);
        System.out.println("test_createGame" + games.get(gameObject.getGameId()) + "mines: " + Arrays.deepToString(mineLocations));
        return gameObject;
    }

    @Override
    public void setMinesOnField(int width, int height, int minesCount) {
        Random random = new Random();
        int minesPlaced = 0;
        while (minesPlaced < minesCount) {
            int row = random.nextInt(height);
            int col = random.nextInt(width);

            if (!mineLocations[row][col]) {
                mineLocations[row][col] = true;
                minesPlaced++;
            }
        }
    }

    @Override
    public GameObject makeGameMove(GameObject gameObject, int row, int col) {
        System.out.println("test_makeGameMove_beforeStep:\n" + games.get(gameObject.getGameId()));
        GameObject currentGame = games.get(gameObject.getGameId());
        if (gameObject.isCompleted()) {
            return currentGame;
        }
        int width = currentGame.getWidth();
        int height = currentGame.getHeight();
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new BadRequestException(" Invalid coordinates ");
        }
        if (revealedCells[row][col]) {
            throw new BadRequestException("Cell already revealed");
        }

        revealedCells[row][col] = true;
        gameField[row][col] = String.valueOf(countMineNeighbors(currentGame, row, col));

        if (mineLocations[row][col]) {
            gameField[row][col] = "X";
            currentGame.setCompleted(true);
            markRemainingCells(currentGame);
            return currentGame;
        }

        if (countMineNeighbors(currentGame, row, col) == 0) {
            openCellNeighbors(currentGame, row, col);
        }
        games.put(currentGame.getGameId(), currentGame);
        System.out.println("test_makeGameMove_afterStep:\n" + games.get(gameObject.getGameId()));
        return currentGame;
    }

    @Override
    public void openCellNeighbors(GameObject gameObject, int row, int col) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int newRow = row + x;
                int newCol = col + y;
                if (newRow >= 0 && newRow < gameObject.getWidth() && newCol >= 0 &&
                        newCol < gameObject.getHeight() && !revealedCells[newRow][newCol]) {
                    revealedCells[newRow][newCol] = true;
                    gameField[newRow][newCol] = String.valueOf(countMineNeighbors(gameObject, newRow, newCol));
                    if (countMineNeighbors(gameObject, newRow, newCol) == 0) {
                        openCellNeighbors(gameObject, newRow, newCol);
                    }
                }
            }
        }

    }

    @Override
    public int countMineNeighbors(GameObject gameObject, int row, int col) {
        int mineNeighborsCount = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                int newRow = row + x;
                int newCol = col + y;
                if (newRow >= 0 && newRow < gameObject.getHeight() && newCol >= 0 && newCol < gameObject.getWidth() &&
                        mineLocations[newRow][newCol]) {
                    mineNeighborsCount++;
                }
            }
        }
        return mineNeighborsCount;
    }

    @Override
    public void markRemainingCells(GameObject gameObject) {
        int width = gameObject.getWidth();
        int height = gameObject.getHeight();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (!revealedCells[row][col]) {
                    if (mineLocations[row][col]) {
                        gameField[row][col] = "M";
                    } else {
                        gameField[row][col] = String.valueOf(countMineNeighbors(gameObject, row, col));
                    }
                }
            }
        }
    }
}
