package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

    private GridPane grid;
    private AbstractWorldMap worldMap;

    @Override
    public void init() throws Exception {
        super.init();
        try {

            //lab7
            System.out.println("LAB7");
            MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
            this.worldMap = new GrassField(10);
            Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
            IEngine engine = new SimulationEngine(directions, this.worldMap, positions);
            this.grid = new GridPane();
            engine.run();
            System.out.println(this.worldMap);


        }
        catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void start(Stage primaryStage) {


        Bounds bounds = this.worldMap.getBounds();
        int width = 400;
        int height = 400;

        grid.setGridLinesVisible(true);
        grid.setHgap(0);
        grid.setVgap(0);

        renderGrid(bounds, height, width);


        Scene scene = new Scene(grid, width, height);
        primaryStage.setTitle("Zwierzak!");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private int calcSizeOfBoxes(int lowerLeft, int upperRight, int lengthOfScene) {
        int howManyBoxes = upperRight - lowerLeft + 2;
        return lengthOfScene / howManyBoxes;

    }
    void renderGrid(Bounds bounds, int height, int width) {
        Vector2d lowerLeft = bounds.lowerLeft();
        Vector2d upperRight = bounds.upperRight();
        int verticalSize = calcSizeOfBoxes(lowerLeft.y(), upperRight.y(), height);
        int horizontalSize = calcSizeOfBoxes(lowerLeft.x(), upperRight.x(), width);
        int lowerLeftX = lowerLeft.x();
        int lowerLeftY = lowerLeft.y();
        int upperRightX = upperRight.x();
        int upperRightY = upperRight.y();
        Label xyLabel = new Label("y\\n");
        GridPane.setHalignment(xyLabel, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(horizontalSize));
        grid.getRowConstraints().add(new RowConstraints(verticalSize));
        grid.add(xyLabel, 0, 0, 1, 1);
        for (int i = lowerLeftY; i <= upperRightY; i++) {
            Label label = new Label(Integer.toString(i));
            grid.add(label, 0, upperRightY - i + 1, 1, 1);
            grid.getRowConstraints().add(new RowConstraints(verticalSize));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int i = lowerLeftX; i <= upperRightX; i++) {
            Label label = new Label(Integer.toString(i));
            grid.add(label, i - lowerLeftX + 1, 0, 1, 1);
            grid.getColumnConstraints().add(new ColumnConstraints(horizontalSize));
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = lowerLeftX; x <= upperRightX; x++) {
            for (int y = lowerLeftY; y <= upperRightY; y++) {
                Vector2d position = new Vector2d(x, y);
                int finalX = x;
                int finalY = y;
                worldMap.objectAt(position).ifPresent(
                            (value) -> {
                                Label label = new Label(value.toString());
                                grid.add(label, finalX - lowerLeftX + 1, upperRightY - finalY + 1, 1, 1);
                                GridPane.setHalignment(label, HPos.CENTER);
                            }
                    );

                }
            }
        }

    }

