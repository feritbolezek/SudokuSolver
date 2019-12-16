package com.feritbolezek.lth;

import com.feritbolezek.lth.constants.Game;
import com.sun.istack.internal.Nullable;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Sudoku {

    private int[][] board;

    private SudokuController sudokuController;

    private boolean visualize = false;


    /**
     * Creates a new Sudoku with an empty board.
     */
    Sudoku() {
        board = new int[Game.ROWS][Game.COLUMNS];
    }

    /**
     * Creates a new Sudoku with an empty board.
     * @param sudokuController optional parameter which allows visualization of the sudoku solving algorithm.
     */
    Sudoku(@Nullable SudokuController sudokuController) {
        board = new int[Game.ROWS][Game.COLUMNS];
        this.sudokuController = sudokuController;
    }

    /**
     *  Solves the Sudoku.
     * @param visualize Set to true if the algorithm should have delays allowing UI to update while running.
     * @return True if solved successfully false otherwise.
     */
    public boolean solve(boolean visualize) {
        this.visualize = visualize;
        return preCheck() && solve(0, 0);
    }

    /**
     * Checks if it's even possible to solve given initial values.
     */
    private boolean preCheck() {


        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {

                if (board[i][j] == 0) {
                    continue;
                }

                int temp = board[i][j];
                board[i][j] = 0;

                if (!withinRules(i,j,temp)) {
                    board[i][j] = temp;
                    return false;
                }
                board[i][j] = temp;
            }
        }
        return true;
    }

    private boolean solve(int i, int j) {

        if (i == Game.ROWS) {
            i = 0;
            j++;
            if (j == Game.COLUMNS)
                return true;
        }

        if (board[i][j] != 0)
            return solve(i + 1, j);

        for (int k = 1; k <= 9; k++) {
            if (withinRules(i, j, k)) {
                board[i][j] = k;

                if (visualize && sudokuController != null) {
                    Platform.runLater(() -> sudokuController.updateAllValues());

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                if (solve(i + 1, j))
                    return true;

            }
        }

        board[i][j] = 0;
        return false;

    }

    /**
     *
     * @param i Row to retrieve from.
     * @param j Column to retrieve from.
     * @return  Value found at the specified location.
     */
    public int getValue(int i, int j) {
        return board[i][j];
    }

    /**
     * Updates the cell value at the specified index.
     * @param i row.
     * @param j column.
     * @param value the new value to update the cell with.
     */
    public void updateTileValue(int i, int j, int value) {
        if (value >= 0 && value <= 9)
            board[i][j] = value;
    }

    private boolean withinRules(int i, int j, int val) {

        for (int k = 0; k < Game.ROWS; k++) { // Checking row
            if (board[i][k] == val)
                return false;
        }

        for (int k = 0; k < Game.COLUMNS; k++) { // Checking column
            if (board[k][j] == val)
                return false;
        }

        int rowPos = (i / 3) * 3;
        int colPos = (j / 3) * 3;

        for (int k = 0; k < 3; k++) { // Checking subsection.
            for (int l = 0; l < 3; l++) {
                if(board[k+rowPos][l+colPos] == val) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Clears the board. (Sets all cells to empty)
     */
    public void clear() {
        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {
                board[i][j] = 0;
            }
        }
    }


    /**
     * Gives the current state of the board in a formatted form.
     * @return Returns the formatted text.
     */
    public String printSudoku() {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            stringBuilder.append("-------------------------------------\n");
            stringBuilder.append("| ");
            for (int j = 0; j < 9; j++) {
                int val = board[i][j];
                stringBuilder.append(val);
                stringBuilder.append(" | ");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("-------------------------------------");

        return stringBuilder.toString();

    }
}
