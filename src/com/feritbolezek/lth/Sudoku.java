package com.feritbolezek.lth;

import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private int[][] board;

    private List<ValueChangedListener> listeners;

    Sudoku() {
        board = new int[9][9];
        listeners = new ArrayList<>();
    }


    public void addListener(ValueChangedListener listener) {
        listeners.add(listener);
    }

    /**
     * Solves the Sudoku.
     *
     * @return True if solved successfully false otherwise.
     */
    public boolean solve() {
        if (preCheck()) {

            boolean res = solve(0, 0);
            System.out.println(res);
            return res;
        }
        return false;
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
                for (ValueChangedListener vcl : listeners) {
                    vcl.onValueChange(i,j,k);
                }


                if (solve(i + 1, j))
                    return true;
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
                for (ValueChangedListener vcl : listeners) {
                    vcl.onValueChange(i,j,0);
                }
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
