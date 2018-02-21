package com.udacity.sandwichclub.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sandwich {

    public class SandwichName {
        @SerializedName("mainName")
        private String mainName;
        @SerializedName("alsoKnownAs")
        private List<String> alsoKnownAs = null;

        public String getMainName() {
            return mainName;
        }

        public void setMainName(String mainName) {
            this.mainName = mainName;
        }

        public List<String> getAlsoKnownAs() {
            return alsoKnownAs;
        }

        public void setAlsoKnownAs(List<String> alsoKnownAs) {
            this.alsoKnownAs = alsoKnownAs;
        }
    }

    @SerializedName("name")
    private SandwichName sandwichName;
    @SerializedName("placeOfOrigin")
    private String placeOfOrigin;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("ingredients")
    private List<String> ingredients = null;

    /**
     * No args constructor for use in serialization
     */
    public Sandwich() {
    }

    public String getMainName() {
        return sandwichName.getMainName();
    }

    public void setMainName(String mainName) {
        this.sandwichName.setMainName(mainName);
    }

    public List<String> getAlsoKnownAs() {
        return this.sandwichName.getAlsoKnownAs();
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.sandwichName.setAlsoKnownAs(alsoKnownAs);
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }
}
