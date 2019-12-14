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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SudokuController extends Application implements Sudoku.ValueChangedListener {


    private Sudoku sudoku;
    private VBox mainContainer;
    private GridPane mainGrid;
    private TilePane[][] tiles = new TilePane[9][9];

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene mainScene = new Scene(root,370,430, Paint.valueOf(Colors.BACKGROUND_COLOR));
        stage.setScene(mainScene);

        sudoku = new Sudoku();
        sudoku.addListener(this);

        mainContainer = new VBox(20);

        root.getChildren().add(mainContainer);

        setupGrid();
        setupBottomBar();
        stage.show();
    }

    private void setupGrid() {
        mainGrid = new GridPane();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TilePane tile = createTile(i,j);
                mainGrid.add(tile,i,j);
                tiles[i][j] = tile;

            }
        }
        mainContainer.getChildren().add(mainGrid);
    }

    private TilePane createTile(int i, int j) {
        TilePane tilePane = new TilePane();
        BorderPane bp = new BorderPane();
        DigitTextField tf = new DigitTextField();

        bp.setCenter(tf);
        bp.setMaxSize(40,40);
        bp.setMinSize(40,40);
        tilePane.setMaxSize(40,40);
        tilePane.setMinSize(40,40);

        tf.setRow(i);
        tf.setColumn(j);

        tf.setStyle("-fx-text-inner-color: white;");
        tf.setAlignment(Pos.BASELINE_CENTER);
        Paint paint = Paint.valueOf(Colors.TILE_COLOR);
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

        solveBtn.setMaxSize(60,8);

        bottomBar.getChildren().add(solveBtn);
        bottomBar.getChildren().add(clearBtn);
        bottomBar.getChildren().add(debugBtn);

        debugBtn.setOnAction((event -> sudoku.printSudoku()) );
        solveBtn.setOnAction(event -> {
            boolean result = sudoku.solve();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    updateValue(i,j);
                }
            }
            
            if (!result) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "NO SOLUTION!");
                alert.show();
            }
        });
        clearBtn.setOnAction(event ->  {
            sudoku.clear();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    updateValue(i,j);
                }
            }
        });

        mainContainer.getChildren().add(bottomBar);
    }

    @Override
    public void onValueChange(int i, int j, int val) {

/*        TilePane tp = tiles[i][j];
        BorderPane bp = (BorderPane) tp.getChildren().get(0);
        DigitTextField dt = (DigitTextField) bp.getCenter();

        if (val == 0) {
            dt.clear();
        } else
            dt.replaceSelection(Integer.toString(val));*/

    }

    private void updateValue(int i, int j) {
        TilePane tp = tiles[i][j];
        BorderPane bp = (BorderPane) tp.getChildren().get(0);
        DigitTextField dt = (DigitTextField) bp.getCenter();

        int val = sudoku.getValue(i,j);
        if (val == 0) {
            dt.clear();
        } else
            dt.replaceSelection(Integer.toString(val));
    }


}
