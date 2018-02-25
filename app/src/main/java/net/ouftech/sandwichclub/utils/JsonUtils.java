package net.ouftech.sandwichclub.utils;

import net.ouftech.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static net.ouftech.sandwichclub.model.Sandwich.*;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        Sandwich sandwich = new Sandwich();

        JSONObject sandwichJson = new JSONObject(json);

        if (sandwichJson.has(NAME_KEY)) {
            JSONObject name = sandwichJson.optJSONObject(NAME_KEY);

            sandwich.setMainName(name.optString(MAIN_NAME_KEY));

            List<String> alsoKnownAsList = getListFromJSONArray(name.optJSONArray(AKAS_KEY));
            sandwich.setAlsoKnownAs(alsoKnownAsList);
        }

        sandwich.setPlaceOfOrigin(sandwichJson.optString(ORIGIN_KEY));

        sandwich.setDescription(sandwichJson.optString(DESCRIPTION_KEY));

        sandwich.setImage(sandwichJson.optString(IMAGE_KEY));

        List<String> alsoKnownAsList = getListFromJSONArray(sandwichJson.optJSONArray(INGREDIENTS_KEY));
        sandwich.setIngredients(alsoKnownAsList);

        return sandwich;
    }

    private static List<String> getListFromJSONArray(JSONArray jsonArray) throws JSONException {

        List<String> alsoKnownAsList = new ArrayList<>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                alsoKnownAsList.add(jsonArray.getString(i));
            }
        }

        return alsoKnownAsList;
    }
}
