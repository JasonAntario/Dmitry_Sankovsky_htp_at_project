package steps.jsonTestIngredients;

import java.util.Arrays;
import java.util.List;

public class Recipe {
    public String recipename;
    public List<Ingredient> ingredlist;
    public int preptime;


    public Recipe(String recipename, Ingredient[] ingredlist, int preptime) {
        this.recipename = recipename;
        this.ingredlist = Arrays.asList(ingredlist);
        this.preptime = preptime;
    }

    public Recipe() {

    }
}
