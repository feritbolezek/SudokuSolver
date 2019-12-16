package com.feritbolezek.lth;

import com.feritbolezek.lth.constants.Colors;
import com.sun.javafx.application.PlatformImpl;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SudokuController extends Application {


    private Sudoku sudoku;
    private VBox mainContainer;
    private TilePane[][] tiles = new TilePane[9][9];


    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene mainScene = new Scene(root,395,450, Paint.valueOf(Colors.BACKGROUND_COLOR));
        stage.setScene(mainScene);
        stage.setTitle("EZ Sudoku Solver");
        stage.getIcons().add(new Image("https://images.assetsdelivery.com/compings_v2/urfandadashov/urfandadashov1808/urfandadashov180815816.jpg"));
        stage.setResizable(false);

        sudoku = new Sudoku(this);

        mainContainer = new VBox(20);

        root.getChildren().add(mainContainer);

        setupGrid();
        setupBottomBar();
        stage.show();
    }

    private void setupGrid() {

        GridPane mainGrid = new GridPane();
        mainGrid.setHgap(5);
        mainGrid.setVgap(5);


        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < 9; j++) {
                TilePane tile = createTile(i,j,colorChoice(i,j));
                mainGrid.add(tile,i,j);
                tiles[i][j] = tile;
            }
        }
        mainContainer.getChildren().add(mainGrid);
    }

    private String colorChoice(int i, int j) {
        if ((j > 2 && j < 6) && (i > 2 && i < 6))
            return Colors.TILE_COLOR;
        else if ((j > 2 && j < 6))
            return Colors.TILE_COLOR_TWO;

        if (i < 3 || i > 5)
            return Colors.TILE_COLOR;
        else
            return Colors.TILE_COLOR_TWO;

    }

    private TilePane createTile(int i, int j, String color) {
        TilePane tilePane = new TilePane();
        BorderPane bp = new BorderPane();
        DigitTextField tf = new DigitTextField();

        bp.setCenter(tf);
        bp.setMaxSize(40,40);
        bp.setMinSize(40,40);
        tilePane.setMaxSize(40,40);
        tilePane.setMinSize(40,40);
        tf.setMaxSize(40,40);

        tf.setRow(i);
        tf.setColumn(j);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(1.0f);
        ds.setColor(Color.color(0f, 0f, 0f));

        tf.setEffect(ds);

        tf.setStyle("-fx-text-inner-color: white;");
        tf.setAlignment(Pos.BASELINE_CENTER);
        tf.setFont(Font.font("Tahoma", FontWeight.BOLD,16));
        Paint paint = Paint.valueOf(color);
        CornerRadii cornerRadii = new CornerRadii(5);
        Insets insets = new Insets(1);
        BackgroundFill bgFill = new BackgroundFill(paint, cornerRadii,insets);
        Background bg = new Background(bgFill);
        tf.setBackground(bg);
        tf.setText("");

        tf.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty())
                sudoku.updateTileValue(tf.getRow(), tf.getColumn(),Integer.parseInt(newValue));
            else
                sudoku.updateTileValue(tf.getRow(), tf.getColumn(),0);
        }));

        tilePane.getChildren().add(bp);

        return tilePane;

    }

    private void setupBottomBar() {
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(0,0,0,10));
        Button solveBtn = new Button("Solve");
        Button clearBtn = new Button("Clear");
        Button debugBtn = new Button("Debug");

        CheckBox visualizeBox = new CheckBox("Visualize (Experimental)");
        visualizeBox.setTextFill(Paint.valueOf(Colors.TEXT_COLOR));

        solveBtn.setMaxSize(60,8);

        bottomBar.getChildren().add(solveBtn);
        bottomBar.getChildren().add(clearBtn);
        bottomBar.getChildren().add(debugBtn);
        bottomBar.getChildren().add(visualizeBox);

        debugBtn.setOnAction((event -> System.out.println(sudoku.printSudoku())) );
        solveBtn.setOnAction(event -> {
            attemptSudokuSolution(visualizeBox.isSelected());
        });
        clearBtn.setOnAction(event ->  {
            sudoku.clear();
            updateAllValues();
        });

        mainContainer.getChildren().add(bottomBar);
    }

    private void attemptSudokuSolution(boolean visualize) {
        if (visualize) {
            new Thread(() -> {
                long startTime = System.currentTimeMillis();
                boolean result = sudoku.solve(true);
                long endTime = System.currentTimeMillis() - startTime;

                displayResult(result,endTime);
            }).start();
        } else {
            long startTime = System.currentTimeMillis();
            boolean result = sudoku.solve(false);
            long endTime = System.currentTimeMillis() - startTime;
            displayResult(result,endTime);
        }
        updateAllValues();
    }

    private void displayResult(boolean result, long time) {
        if (!result) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No solution! It took " + time + " ms to figure that out.");
                alert.show();
            });
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Solution found! It took " + time + " ms to find it.");
                alert.show();
            });
        }
    }

    /**
     * Updates all cell values on the UI (the TextField values).
     */
    public void updateAllValues() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                updateValue(i,j);
            }
        }
    }

    private void updateValue(int i, int j) {
        TilePane tp = tiles[i][j];
        BorderPane bp = (BorderPane) tp.getChildren().get(0);
        DigitTextField dt = (DigitTextField) bp.getCenter();

        int val = sudoku.getValue(i,j);
        if (val == 0) {
            dt.clear();
        } else
            dt.setText(Integer.toString(val));
    }


}
