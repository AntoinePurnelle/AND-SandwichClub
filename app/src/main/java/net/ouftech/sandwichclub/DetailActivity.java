package net.ouftech.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.ouftech.sandwichclub.model.Sandwich;
import net.ouftech.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.detail_image_iv)
    ImageView imageIV;
    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.detail_aka_label_tv)
    TextView akaLabelTv;
    @BindView(R.id.detail_aka_tv)
    TextView akaTv;
    @BindView(R.id.detail_origin_label_tv)
    TextView originLabelTv;
    @BindView(R.id.detail_origin_tv)
    TextView originTv;
    @BindView(R.id.detail_detail_description_label_tv)
    TextView descriptionLabelTv;
    @BindView(R.id.detail_description_tv)
    TextView descriptionTv;
    @BindView(R.id.detail_detail_ingredients_label_tv)
    TextView ingredientsLabelTv;
    @BindView(R.id.detail_ingredients_tv)
    TextView ingredientsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        // Collapsing toolbar layout inspired from http://www.tutos-android.com/design-support-library-collapsingtoolbarlayout
        setSupportActionBar(toolbar);
        ViewCompat.setTransitionName(findViewById(R.id.appBarLayout), "Name");
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            Log.e("DetailActivity", "Error while parsing JSON", e);
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.ic_sandwich)
                .into(imageIV);

        setTitle(sandwich.getMainName());
        collapsingToolbar.setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        if (sandwich.getAlsoKnownAs() == null || sandwich.getAlsoKnownAs().isEmpty()) {
            akaLabelTv.setVisibility(View.GONE);
            akaTv.setVisibility(View.GONE);
        } else {
            akaTv.setText(toBulletPoints(sandwich.getAlsoKnownAs()));
        }

        if (TextUtils.isEmpty(sandwich.getPlaceOfOrigin())) {
            originLabelTv.setVisibility(View.GONE);
            originTv.setVisibility(View.GONE);
        } else {
            originTv.setText(sandwich.getPlaceOfOrigin());
        }

        if (TextUtils.isEmpty(sandwich.getDescription())) {
            descriptionLabelTv.setVisibility(View.GONE);
            descriptionTv.setVisibility(View.GONE);
        } else {
            descriptionTv.setText(sandwich.getDescription());
        }

        if (sandwich.getIngredients() == null || sandwich.getIngredients().isEmpty()) {
            ingredientsLabelTv.setVisibility(View.GONE);
            ingredientsLabelTv.setVisibility(View.GONE);
        } else {
            ingredientsTv.setText(toBulletPoints(sandwich.getIngredients()));
        }
    }

    private String toBulletPoints(@NonNull List<String> list) {
        if (list.size() == 1)
            return list.get(0);

        return "\u2022 " + TextUtils.join("\n\u2022 ", list);
    }
}
