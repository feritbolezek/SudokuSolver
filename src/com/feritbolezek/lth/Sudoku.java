package com.feritbolezek.lth;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Sudoku {

    private int[][] board;

    private SudokuController sudokuController;

    private boolean visualize = false;

    Sudoku(SudokuController sudokuController) {
        board = new int[9][9];
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


        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

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


        if (i == 9) {
            i = 0;
            if (++j == 9) {
                return true;
            }
        }

        if (board[i][j] != 0) {
            return solve(i + 1, j);
        }

        for (int k = 1; k <= 9; k++) {
            if (withinRules(i, j, k)) {
                board[i][j] = k;

                if (visualize) {
                    Platform.runLater(() -> sudokuController.updateAllValues());

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                if (solve(i + 1, j)) {
                    return true;
                }
            }
        }

        board[i][j] = 0;
        return false;

    }

    public int getValue(int i, int j) {
        return board[i][j];
    }

    public void updateTileValue(int i, int j, int value) {
        board[i][j] = value;
    }

    private boolean withinRules(int i, int j, int val) {

        for (int k = 0; k < 9; k++) { // Checking row
            if (board[i][k] == val)
                return false;
        }

        for (int k = 0; k < 9; k++) { // Checking column
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
     * Clears the board.
     */
    public void clear() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Prints out values of each tile on the grid. One of the 9 grids are chosen [0-8]. Going from left to right.
     */
    public void printSudoku() {
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                int val = board[i][j];
                System.out.print(val + " | ");
            }
            System.out.println(" ");
            System.out.println("-------------------------------------------------------------------");
        }
    }

    interface ValueChangedListener {
        void onValueChange(int i, int j, int val);
    }

}
