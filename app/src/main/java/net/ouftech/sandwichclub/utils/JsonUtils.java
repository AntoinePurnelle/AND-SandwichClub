package net.ouftech.sandwichclub.utils;

import net.ouftech.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        Sandwich sandwich = new Sandwich();

        JSONObject sandwichJson = new JSONObject(json);

        if (sandwichJson.has("name")) {
            JSONObject name = sandwichJson.getJSONObject("name");

            if (name.has("mainName"))
                sandwich.setMainName(name.getString("mainName"));

            if (name.has("alsoKnownAs")) {
                List<String> alsoKnownAsList = getListFromJSONArray(name.getJSONArray("alsoKnownAs"));
                sandwich.setAlsoKnownAs(alsoKnownAsList);
            }
        }

        if (sandwichJson.has("placeOfOrigin"))
            sandwich.setPlaceOfOrigin(sandwichJson.getString("placeOfOrigin"));

        if (sandwichJson.has("description"))
            sandwich.setDescription(sandwichJson.getString("description"));

        if (sandwichJson.has("image"))
            sandwich.setImage(sandwichJson.getString("image"));

        if (sandwichJson.has("ingredients")) {
            List<String> alsoKnownAsList = getListFromJSONArray(sandwichJson.getJSONArray("ingredients"));
            sandwich.setIngredients(alsoKnownAsList);
        }

        return sandwich;
    }

    private static List<String> getListFromJSONArray(JSONArray jsonArray) throws JSONException {
        List<String> alsoKnownAsList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            alsoKnownAsList.add(jsonArray.getString(i));
        }

        return alsoKnownAsList;
    }
}
