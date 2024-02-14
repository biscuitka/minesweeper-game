package com.biscuitka.minesweeper.repository;

import com.biscuitka.minesweeper.model.GameObject;

public interface FieldRepository {
    GameObject createGame(GameObject gameObject);

    void setMinesOnField(int width, int height, int minesCount);

    GameObject makeGameMove(GameObject gameObject, int row, int col);

    void openCellNeighbors(GameObject gameObject, int row, int col);

    int countMineNeighbors(GameObject gameObject, int row, int col);

    void markRemainingCells(GameObject gameObject);
}
