package TowerDefense.GUI;

import javafx.application.Application;
import javafx.stage.Stage;



public class App extends Application {

    private JsonReader config;
    @Override
    public void init() throws Exception {
        super.init();
        // load configuration settings from Json
        String pathToConfig = "src/main/java/TowerDefense/config.json";
        String objectToLoad = "ConfigurationFile";
        this.config = new JsonReader(pathToConfig, objectToLoad);

        //


    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
