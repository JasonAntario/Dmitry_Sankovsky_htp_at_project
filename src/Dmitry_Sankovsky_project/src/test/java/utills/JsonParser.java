package utills;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;
import steps.jsonTestIngredients.Ingredient;
import steps.jsonTestIngredients.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {

    private final static String JSON = "src\\Dmitry_Sankovsky_project\\src\\test\\java\\resorces\\recipe.json";
    File file = new File(JSON);

    public void parseJSON() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj = new JSONObject(input);
        System.out.println(obj.getString("recipename"));
    }

    public void parseGson() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);
        System.out.println(recipe.recipename);
    }

   public void parseJackson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipe = mapper.readValue(file, Recipe.class);
        System.out.println(recipe.recipename);
    }

    public void fromGson() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = new Recipe("borsh", new Ingredient[]{}, 120);
        System.out.println(gson.toJson(recipe));
    }
}
