package com.feritbolezek.lth;

import org.junit.*;
import com.feritbolezek.lth.constants.Game;

public class SudokuTest {

    private Sudoku sudoku;

    @Before
    public void setUp() {
        sudoku = new Sudoku();
    }

    @After
    public void tearDown() {
        sudoku = null;
    }

    @Test
    public void addToBoard() {

        for (int i = 0; i < Game.ROWS; i++) {       // Testing that all values = 0 before adding anything.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected empty board (0 on all cells) got: " +
                        sudoku.getValue(i,j) + " on cell:  [" + i + "," + j + "]",0,sudoku.getValue(i,j));
            }
        }

        /***************************************************************************
         * Testing adding at specific indexes. (randomly chosen)
        /****************************************************************************/

        sudoku.updateTileValue(1,5,7);
        sudoku.updateTileValue(1,3,2);
        sudoku.updateTileValue(2,5,1);
        sudoku.updateTileValue(6,5,3);
        sudoku.updateTileValue(3,7,1);

        Assert.assertEquals("Expected 7 got: " + sudoku.getValue(1,5) + " on cell: [" + 1 + "," + 5 + "]",7,sudoku.getValue(1,5));
        Assert.assertEquals("Expected 2 got: " + sudoku.getValue(1,3) + " on cell: [" + 1 + "," + 3 + "]",2,sudoku.getValue(1,3));
        Assert.assertEquals("Expected 1 got: " + sudoku.getValue(2,5) + " on cell: [" + 2 + "," + 5 + "]",1,sudoku.getValue(2,5));
        Assert.assertEquals("Expected 3 got: " + sudoku.getValue(6,5) + " on cell: [" + 6 + "," + 5 + "]",3,sudoku.getValue(6,5));
        Assert.assertEquals("Expected 1 got: " + sudoku.getValue(3,7) + " on cell: [" + 3 + "," + 7 + "]",1,sudoku.getValue(3,7));


        /***************************************************************************
         * Testing overwriting already existing values
         /****************************************************************************/

        sudoku.updateTileValue(1,5,2);
        sudoku.updateTileValue(1,3,8);

        Assert.assertEquals("Expected 2 got: " + sudoku.getValue(1,5) + " on cell: [" + 1 + "," + 5 + "]",2,sudoku.getValue(1,5));
        Assert.assertEquals("Expected 8 got: " + sudoku.getValue(1,3) + " on cell: [" + 1 + "," + 3 + "]",8,sudoku.getValue(1,3));


        /***************************************************************************
         * Testing overwriting with incorrect values
         /****************************************************************************/

        try {
            sudoku.updateTileValue(1, 5, 18);
            sudoku.updateTileValue(1, 3, 38);
            Assert.fail("Expected continued execution in catch block.");
        } catch (IllegalArgumentException e) {
            // Great!
        }

        Assert.assertEquals("Expected 2 got: " + sudoku.getValue(1,5) + " on cell: [" + 1 + "," + 5 + "]",2,sudoku.getValue(1,5));
        Assert.assertEquals("Expected 8 got: " + sudoku.getValue(1,3) + " on cell: [" + 1 + "," + 3 + "]",8,sudoku.getValue(1,3));

    }

    @Test
    public void removeFromBoard() {
        for (int i = 0; i < Game.ROWS; i++) {       // Testing that all values = 0 before doing anything.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected empty board (0 on all cells) got: " +
                        sudoku.getValue(i,j) + " on cell:  [" + i + "," + j + "]",0,sudoku.getValue(i,j));
            }
        }

        /***************************************************************************
         * Testing adding and then removing. (Removing is replacing all cells with a value of 0)
         /****************************************************************************/

        sudoku.updateTileValue(1,5,7);
        sudoku.updateTileValue(1,3,2);
        sudoku.updateTileValue(2,5,1);
        sudoku.updateTileValue(6,5,3);
        sudoku.updateTileValue(3,7,1);

        Assert.assertEquals("Expected 7 got: " + sudoku.getValue(1,5) + " on cell: [" + 1 + "," + 5 + "]",7,sudoku.getValue(1,5));
        Assert.assertEquals("Expected 2 got: " + sudoku.getValue(1,3) + " on cell: [" + 1 + "," + 3 + "]",2,sudoku.getValue(1,3));
        Assert.assertEquals("Expected 1 got: " + sudoku.getValue(2,5) + " on cell: [" + 2 + "," + 5 + "]",1,sudoku.getValue(2,5));
        Assert.assertEquals("Expected 3 got: " + sudoku.getValue(6,5) + " on cell: [" + 6 + "," + 5 + "]",3,sudoku.getValue(6,5));
        Assert.assertEquals("Expected 1 got: " + sudoku.getValue(3,7) + " on cell: [" + 3 + "," + 7 + "]",1,sudoku.getValue(3,7));


        sudoku.updateTileValue(1,5,0);
        sudoku.updateTileValue(1,3,0);
        sudoku.updateTileValue(2,5,0);
        sudoku.updateTileValue(6,5,0);
        sudoku.updateTileValue(3,7,0);

        for (int i = 0; i < Game.ROWS; i++) {       // Testing that all values = 0.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected empty board (0 on all cells) got: " +
                        sudoku.getValue(i,j) + " on cell:  [" + i + "," + j + "]",0,sudoku.getValue(i,j));
            }
        }


    }




    /**********************************************************************************************
        Expected result is shown below.
        WARNING: Print values are hardcoded and easy to mess up if small changes are made to the printSudoku() method.
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------

     **********************************************************************************************/


     @Test
    public void printEmptyBoard() {
        String txt = "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------";

        Assert.assertEquals("The received print txt from sudoku does not match the expected.",
                txt,
                sudoku.printSudoku());


    }


    /**********************************************************************************************
     Expected result is shown below.
     WARNING: Print values are hardcoded and easy to mess up if small changes are made to the printSudoku() method.
     -------------------------------------
     | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
     -------------------------------------
     | 4 | 5 | 6 | 7 | 8 | 9 | 1 | 2 | 3 |
     -------------------------------------
     | 7 | 8 | 9 | 1 | 2 | 3 | 4 | 5 | 6 |
     -------------------------------------
     | 2 | 1 | 4 | 3 | 6 | 5 | 8 | 9 | 7 |
     -------------------------------------
     | 3 | 6 | 5 | 8 | 9 | 7 | 2 | 1 | 4 |
     -------------------------------------
     | 8 | 9 | 7 | 2 | 1 | 4 | 3 | 6 | 5 |
     -------------------------------------
     | 5 | 3 | 1 | 6 | 4 | 2 | 9 | 7 | 8 |
     -------------------------------------
     | 6 | 4 | 2 | 9 | 7 | 8 | 5 | 3 | 1 |
     -------------------------------------
     | 9 | 7 | 8 | 5 | 3 | 1 | 6 | 4 | 2 |
     -------------------------------------

     **********************************************************************************************/


    @Test
    public void printFullBoard() {
        String txt = "-------------------------------------\n" +
                "| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | \n" +
                "-------------------------------------\n" +
                "| 4 | 5 | 6 | 7 | 8 | 9 | 1 | 2 | 3 | \n" +
                "-------------------------------------\n" +
                "| 7 | 8 | 9 | 1 | 2 | 3 | 4 | 5 | 6 | \n" +
                "-------------------------------------\n" +
                "| 2 | 1 | 4 | 3 | 6 | 5 | 8 | 9 | 7 | \n" +
                "-------------------------------------\n" +
                "| 3 | 6 | 5 | 8 | 9 | 7 | 2 | 1 | 4 | \n" +
                "-------------------------------------\n" +
                "| 8 | 9 | 7 | 2 | 1 | 4 | 3 | 6 | 5 | \n" +
                "-------------------------------------\n" +
                "| 5 | 3 | 1 | 6 | 4 | 2 | 9 | 7 | 8 | \n" +
                "-------------------------------------\n" +
                "| 6 | 4 | 2 | 9 | 7 | 8 | 5 | 3 | 1 | \n" +
                "-------------------------------------\n" +
                "| 9 | 7 | 8 | 5 | 3 | 1 | 6 | 4 | 2 | \n" +
                "-------------------------------------";

        int[][] nums = { {1,2,3,4,5,6,7,8,9},
                         {4,5,6,7,8,9,1,2,3},
                         {7,8,9,1,2,3,4,5,6},
                         {2,1,4,3,6,5,8,9,7},
                         {3,6,5,8,9,7,2,1,4},
                         {8,9,7,2,1,4,3,6,5},
                         {5,3,1,6,4,2,9,7,8},
                         {6,4,2,9,7,8,5,3,1},
                         {9,7,8,5,3,1,6,4,2} };

        for (int i = 0; i < Game.ROWS; i++) {
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,nums[i][j]);
            }
        }

        Assert.assertEquals("The received print txt from sudoku does not match the expected.",
                txt,
                sudoku.printSudoku());

    }


    /**********************************************************************************************
     Expected result is shown below.
     WARNING: Print values are hardcoded and easy to mess up if small changes are made to the printSudoku() method.
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 1 | 3 | 3 | 7 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------

     **********************************************************************************************/


    @Test
    public void manipulateAndPrintBoard() {

        String txt = "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 1 | 3 | 3 | 7 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------\n" +
                "| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | \n" +
                "-------------------------------------";

        sudoku.updateTileValue(4,2,1);
        sudoku.updateTileValue(4,3,3);
        sudoku.updateTileValue(4,4,3);
        sudoku.updateTileValue(4,5,7);

        Assert.assertEquals("The received print txt from sudoku does not match the expected.",
                txt,
                sudoku.printSudoku());
    }

    /*********************************************************************************************
     Expected result is shown below.
     WARNING: Print values are hardcoded and easy to mess up if small changes are made to the printSudoku() method.
     -------------------------------------
     | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
     -------------------------------------
     | 4 | 5 | 6 | 7 | 8 | 9 | 1 | 2 | 3 |
     -------------------------------------
     | 7 | 8 | 9 | 1 | 2 | 3 | 4 | 5 | 6 |
     -------------------------------------
     | 2 | 1 | 4 | 3 | 6 | 5 | 8 | 9 | 7 |
     -------------------------------------
     | 3 | 6 | 5 | 8 | 9 | 7 | 2 | 1 | 4 |
     -------------------------------------
     | 8 | 9 | 7 | 2 | 1 | 4 | 3 | 6 | 5 |
     -------------------------------------
     | 5 | 3 | 1 | 6 | 4 | 2 | 9 | 7 | 8 |
     -------------------------------------
     | 6 | 4 | 2 | 9 | 7 | 8 | 5 | 3 | 1 |
     -------------------------------------
     | 9 | 7 | 8 | 5 | 3 | 1 | 6 | 4 | 2 |
     -------------------------------------
     *********************************************************************************************/
    @Test
    public void solveEmptyBoard() {
        Assert.assertTrue(sudoku.solve(false)); // Should return true if solved successfully

        int[][] nums = { {1,2,3,4,5,6,7,8,9},
                         {4,5,6,7,8,9,1,2,3},
                         {7,8,9,1,2,3,4,5,6},
                         {2,1,4,3,6,5,8,9,7},
                         {3,6,5,8,9,7,2,1,4},
                         {8,9,7,2,1,4,3,6,5},
                         {5,3,1,6,4,2,9,7,8},
                         {6,4,2,9,7,8,5,3,1},
                         {9,7,8,5,3,1,6,4,2} };

        for (int i = 0; i < Game.ROWS; i++) {       // Making sure that the board actually has a correct solution.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected the same value. failed at cell: [" + i + "," + j + "]",
                        nums[j][i],
                        sudoku.getValue(i,j));
            }
        }

    }


    /**************************************************************************************************************
      +++++++++++++INPUT+++++++++++++++++
     -------------------------------------
     | 0 | 0 | 1 | 0 | 0 | 6 | 4 | 8 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 5 | 0 | 1 | 6 | 0 |
     -------------------------------------
     | 8 | 0 | 2 | 0 | 0 | 0 | 0 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 5 | 2 | 0 | 0 | 6 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 1 | 0 | 0 | 0 | 3 | 0 |
     -------------------------------------
     | 9 | 0 | 0 | 0 | 0 | 0 | 8 | 0 | 0 |
     -------------------------------------
     | 0 | 0 | 0 | 0 | 6 | 0 | 0 | 1 | 4 |
     -------------------------------------
     | 6 | 0 | 0 | 9 | 0 | 2 | 0 | 0 | 0 |
     -------------------------------------
     | 2 | 5 | 0 | 0 | 0 | 8 | 0 | 0 | 0 |
     -------------------------------------

       +++++++++++EXPECTED+++++++++++++
     -------------------------------------
     | 5 | 3 | 1 | 7 | 2 | 6 | 4 | 8 | 9 |
     -------------------------------------
     | 4 | 7 | 9 | 8 | 5 | 3 | 1 | 6 | 2 |
     -------------------------------------
     | 8 | 6 | 2 | 4 | 9 | 1 | 5 | 7 | 3 |
     -------------------------------------
     | 1 | 8 | 5 | 2 | 3 | 9 | 6 | 4 | 7 |
     -------------------------------------
     | 7 | 2 | 6 | 1 | 8 | 4 | 9 | 3 | 5 |
     -------------------------------------
     | 9 | 4 | 3 | 6 | 7 | 5 | 8 | 2 | 1 |
     -------------------------------------
     | 3 | 9 | 8 | 5 | 6 | 7 | 2 | 1 | 4 |
     -------------------------------------
     | 6 | 1 | 7 | 9 | 4 | 2 | 3 | 5 | 8 |
     -------------------------------------
     | 2 | 5 | 4 | 3 | 1 | 8 | 7 | 9 | 6 |
     -------------------------------------

     **************************************************************************************************************/

    @Test
    public void solveSpecificBoard() {     // Solves the board given with the assignment.
        int[][] problem = { {0,0,8,0,0,9,0,6,2},
                            {0,0,0,0,0,0,0,0,5},
                            {1,0,2,5,0,0,0,0,0},
                            {0,0,0,2,1,0,0,9,0},
                            {0,5,0,0,0,0,6,0,0},
                            {6,0,0,0,0,0,0,2,8},
                            {4,1,0,6,0,8,0,0,0},
                            {8,6,0,0,3,0,1,0,0},
                            {0,0,0,0,0,0,4,0,0} };

        int[][] solution = { {5,4,8,1,7,9,3,6,2},
                             {3,7,6,8,2,4,9,1,5},
                             {1,9,2,5,6,3,8,7,4},
                             {7,8,4,2,1,6,5,9,3},
                             {2,5,9,3,8,7,6,4,1},
                             {6,3,1,9,4,5,7,2,8},
                             {4,1,5,6,9,8,2,3,7},
                             {8,6,7,4,3,2,1,5,9},
                             {9,2,3,7,5,1,4,8,6} };


        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertTrue(sudoku.solve(false)); // Should return true if solved successfully

        for (int i = 0; i < Game.ROWS; i++) {       // Making sure that the board actually has a correct solution.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected the same value. failed at cell: [" + i + "," + j + "]",
                        solution[i][j],
                        sudoku.getValue(i,j));
            }
        }
    }
    /***************************************************************************
      Solves a board that is fairly easy to solve.
     /****************************************************************************/
    @Test
    public void solveSpecificBoardEasy() {
        int[][] problem = { {7,0,1,0,2,5,0,4,0},
                            {0,0,5,6,1,0,2,0,0},
                            {6,0,0,0,9,0,0,0,0},
                            {0,7,0,0,0,0,0,0,0},
                            {0,0,2,0,0,0,0,3,0},
                            {0,0,0,0,0,8,0,0,0},
                            {0,0,7,0,0,0,3,9,0},
                            {1,0,0,9,5,0,0,0,4},
                            {0,0,9,0,3,2,0,1,7} };

        int[][] solution = { {7,3,1,8,2,5,9,4,6},
                             {9,8,5,6,1,4,2,7,3},
                             {6,2,4,7,9,3,5,8,1},
                             {5,7,8,3,4,9,1,6,2},
                             {4,9,2,5,6,1,7,3,8},
                             {3,1,6,2,7,8,4,5,9},
                             {2,4,7,1,8,6,3,9,5},
                             {1,6,3,9,5,7,8,2,4},
                             {8,5,9,4,3,2,6,1,7} };


        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertTrue(sudoku.solve(false)); // Should return true if solved successfully

        for (int i = 0; i < Game.ROWS; i++) {       // Making sure that the board actually has a correct solution.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected the same value. failed at cell: [" + i + "," + j + "]",
                        solution[i][j],
                        sudoku.getValue(i,j));
            }
        }
    }

    /***************************************************************************
     Solves a board that is fairly difficult to solve.
     /****************************************************************************/

    @Test
    public void solveSpecificBoardMedium() {
        int[][] problem = { {0,0,0,0,0,0,6,3,1},
                            {0,0,0,0,4,0,0,0,8},
                            {3,1,0,0,0,6,0,4,0},
                            {5,0,2,0,0,0,0,9,4},
                            {0,0,3,0,0,0,8,0,2},
                            {0,7,0,0,0,0,3,0,6},
                            {0,0,0,9,0,0,0,2,7},
                            {9,2,4,6,7,0,0,8,0},
                            {7,5,8,0,0,0,0,6,0} };

        int[][] solution = { {4,8,5,2,9,7,6,3,1},
                             {2,9,6,3,4,1,5,7,8},
                             {3,1,7,8,5,6,2,4,9},
                             {5,6,2,1,3,8,7,9,4},
                             {1,4,3,7,6,9,8,5,2},
                             {8,7,9,5,2,4,3,1,6},
                             {6,3,1,9,8,5,4,2,7},
                             {9,2,4,6,7,3,1,8,5},
                             {7,5,8,4,1,2,9,6,3} };


        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertTrue(sudoku.solve(false)); // Should return true if solved successfully

        for (int i = 0; i < Game.ROWS; i++) {       // Making sure that the board actually has a correct solution.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected the same value. failed at cell: [" + i + "," + j + "]",
                        solution[i][j],
                        sudoku.getValue(i,j));
            }
        }
    }

    /***************************************************************************
     Solves a board that is difficult to solve.
     /****************************************************************************/

    @Test
    public void solveSpecificBoardHard() {
        int[][] problem = { {0,0,6,0,0,7,0,0,0},
                            {0,0,0,8,0,0,2,0,5},
                            {0,0,0,2,4,0,0,0,0},
                            {0,4,0,0,0,9,7,0,0},
                            {0,0,0,0,7,0,0,0,6},
                            {7,2,0,0,0,1,5,0,8},
                            {0,9,0,0,0,0,0,8,0},
                            {0,0,0,0,0,0,0,4,0},
                            {0,0,4,0,1,0,6,0,7} };

        int[][] solution = { {2,8,6,1,5,7,9,3,4},
                             {4,7,1,8,9,3,2,6,5},
                             {3,5,9,2,4,6,8,7,1},
                             {6,4,8,5,2,9,7,1,3},
                             {9,1,5,3,7,8,4,2,6},
                             {7,2,3,4,6,1,5,9,8},
                             {5,9,7,6,3,4,1,8,2},
                             {1,6,2,7,8,5,3,4,9},
                             {8,3,4,9,1,2,6,5,7} };


        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertTrue(sudoku.solve(false)); // Should return true if solved successfully

        for (int i = 0; i < Game.ROWS; i++) {       // Making sure that the board actually has a correct solution.
            for (int j = 0; j < Game.COLUMNS; j++) {
                Assert.assertEquals("Expected the same value. failed at cell: [" + i + "," + j + "]",
                        solution[i][j],
                        sudoku.getValue(i,j));
            }
        }
    }

    /***************************************************************************
     Attempts to solve a board that is unsolvable.
     /****************************************************************************/

    @Test
    public void solveUnsolvableBoard1() {       // Attempts to solve the board (unsolvable) given with the assignment.
        int[][] problem = { {1,2,3,0,0,0,0,0,0},
                            {4,5,6,0,0,0,0,0,0},
                            {0,0,0,7,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0} };

        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertFalse(sudoku.solve(false)); // Should return true if solved successfully
    }

    /***************************************************************************
     Attempts to solve a board that is unsolvable. (Clash in col 3 (2's))
     /****************************************************************************/

    @Test
    public void solveUnsolvableBoard2() {
        int[][] problem = { {0,0,0,0,0,0,0,0,0},
                            {0,1,0,0,0,0,5,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,2,0,0,0,9,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,7,0,0,0,0,3,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,2,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0} };

        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertFalse(sudoku.solve(false)); // Should return true if solved successfully
    }

    /***************************************************************************
     Attempts to solve a board that is unsolvable. (Clash in row 8 (3's))
     /****************************************************************************/

    @Test
    public void solveUnsolvableBoard3() {
        int[][] problem = { {0,0,0,0,3,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,2,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,4,0,0},
                            {0,1,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,3,0,0,0,0,0,3,0} };

        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertFalse(sudoku.solve(false)); // Should return true if solved successfully
    }

    /***************************************************************************
     Attempts to solve a board that is unsolvable. (Clash in first block (7's))
     /****************************************************************************/

    @Test
    public void solveUnsolvableBoard4() {
        int[][] problem = { {7,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,7,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0,0,0} };

        for (int i = 0; i < Game.ROWS; i++) { // Add the values to the board.
            for (int j = 0; j < Game.COLUMNS; j++) {
                sudoku.updateTileValue(i,j,problem[i][j]);
            }
        }

        Assert.assertFalse(sudoku.solve(false)); // Should return true if solved successfully
    }



}