package TowerDefense.GUI;

import TowerDefense.Simulation.SimulationEngine;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.List;

public class MenuBar {

    private Thread engineThread;
    private final SimulationEngine engine;
    private final List<IElementToPlaceObserver> elementToPlaceObservers = new LinkedList<>();
    private VBox rightPane;


    public MenuBar(Thread engineThread, SimulationEngine engine) {
        this.engineThread = engineThread;
        this.engine = engine;
        createRightPane();

    }

    public void createRightPane() {

        VBox rightPane = new VBox();
        Button button = new Button("Run");
        button.setOnAction(event -> {

            this.engineThread = new Thread(this.engine);
            this.engineThread.start();
//            try {
//                clearAndGenerateNewGrid();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }
        });
        button.setMinWidth(20);
        button.setMinHeight(20);

        Button towerButton = new Button("Tower");
        towerButton.setOnAction(event -> selectedElementChanged(ObjectsToPlace.TOWER));
        Button wallButton = new Button("Wall");
        wallButton.setOnAction(event -> selectedElementChanged(ObjectsToPlace.WALL));
        rightPane.getChildren().addAll(button, towerButton, wallButton);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setSpacing(15);
        this.rightPane = rightPane;
    }

    public void addSelectedElementObserver(IElementToPlaceObserver observer) {
        this.elementToPlaceObservers.add(observer);
    }
    private void selectedElementChanged(ObjectsToPlace toPlace) {
        for (IElementToPlaceObserver observer: this.elementToPlaceObservers) {
            observer.selectedElementChanged(toPlace);
        }
    }

    public VBox getRightPane() {
        return this.rightPane;
    }


}
