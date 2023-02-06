package TowerDefense.GUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    private final JSONObject config;

    public JsonReader(String pathToConfig, String objectToLoad) throws IOException, JSONException {
        try {
            String contents = new String((Files.readAllBytes(Paths.get(pathToConfig))));
            this.config = new JSONObject(contents).getJSONObject(objectToLoad);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Plik do wczytania nie istnieje");
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public String getConfig(String elementOfConfig) {
        if (!this.config.has(elementOfConfig)) {
            System.out.println("Żądany element" + elementOfConfig + "nie znajduje się w wczytanym pliku");
        }
        return this.config.getString(elementOfConfig);

    }

    public JSONObject getConfig() { // in case u need this to debug etc.
        return config;
    }

}
