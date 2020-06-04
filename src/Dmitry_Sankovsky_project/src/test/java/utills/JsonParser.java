package utills;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {

    private final static String JSON = "src\\Dmitry_Sankovsky_project\\src\\test\\java\\resources\\recipe.json";
    private File file = new File(JSON);

    public void parseJSON(FileReader fileReader) throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj = new JSONObject(input);
        System.out.println(obj.getString("preptime"));
    }

    public void parseGson() {
        Gson gson = new Gson();
        //Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);
       // System.out.println(recipe.recipename);
    }

   public void parseJackson(){
        ObjectMapper mapper = new ObjectMapper();
       // Recipe recipe = mapper.readValue(file, Recipe.class);
      //  System.out.println(recipe.recipename);
    }

    public void fromGson() {
        Gson gson = new Gson();
        //Recipe recipe = new Recipe("borsh", new Ingredient[]{}, 120);
       // System.out.println(gson.toJson(recipe));
    }

}
