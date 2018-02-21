package com.udacity.sandwichclub.utils;

import com.google.gson.Gson;
import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        return new Gson().fromJson(json, Sandwich.class);
    }
}
