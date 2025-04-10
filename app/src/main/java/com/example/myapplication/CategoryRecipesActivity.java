package com.example.myapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoryRecipesActivity extends AppCompatActivity {
    TextView categoryTitle;
    TextView recipeName1, recipeName2, recipeName3;
    ImageView recipeImage1, recipeImage2, recipeImage3, categoryImage;

    int[] imageList = {
            R.drawable.breakfastc,
            R.drawable.saladc,
            R.drawable.soupc,
            R.drawable.pastac,
            R.drawable.fishc,
            R.drawable.meatc,
            R.drawable.burgerc,
            R.drawable.dessertc
    };

    String[] recipeImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_recipes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        categoryTitle = findViewById(R.id.categoryName);
        recipeName1 = findViewById(R.id.recipeName1);
        recipeName2 = findViewById(R.id.recipeName2);
        recipeName3 = findViewById(R.id.recipeName3);

        recipeImage1 = findViewById(R.id.recipeImage1);
        recipeImage2 = findViewById(R.id.recipeImage2);
        recipeImage3 = findViewById(R.id.recipeImage3);

        categoryImage = findViewById(R.id.categoryImage);

        Intent intent = getIntent();
        String categoryName = intent.getStringExtra("CATEGORY_NAME");
        categoryTitle.setText(categoryName);

        int recipeArrayId = getResources().getIdentifier(categoryName, "array", getPackageName());
        int imageArrayId = getResources().getIdentifier(categoryName + "_Images", "array", getPackageName());

        String[] categoryNames = getResources().getStringArray(R.array.category_names);


        for (int i = 0; i < categoryNames.length; i++) {
            if (categoryNames[i].equals(categoryName)) {
                categoryImage.setImageResource(imageList[i]);
                break;
            }
        }

        if (recipeArrayId != 0 && imageArrayId != 0) {
            String[] recipeNames = getResources().getStringArray(recipeArrayId);
            String[] recipeImages = getResources().getStringArray(imageArrayId);

            recipeName1.setText(recipeNames[0]);
            recipeName2.setText(recipeNames[1]);
            recipeName3.setText(recipeNames[2]);

            recipeImage1.setImageResource(getResources().getIdentifier(recipeImages[0], "drawable", getPackageName()));
            recipeImage2.setImageResource(getResources().getIdentifier(recipeImages[1], "drawable", getPackageName()));
            recipeImage3.setImageResource(getResources().getIdentifier(recipeImages[2], "drawable", getPackageName()));


        }

    }

    public void selectRecipe(View view) {
        String recipeName = ((TextView) view).getText().toString();
        Intent intent = new Intent(CategoryRecipesActivity.this, RecipeActivity.class);
        String imageName = "";

        intent.putExtra("CATEGORY_NAME", categoryTitle.getText().toString());
        intent.putExtra("RECIPE_NAME", recipeName);



        if (view.getId() == R.id.recipeName1) {
            imageName = getResources().getStringArray(getResources().getIdentifier(categoryTitle.getText().toString() + "_Images", "array", getPackageName()))[0];
        } else if (view.getId() == R.id.recipeName2) {
            imageName = getResources().getStringArray(getResources().getIdentifier(categoryTitle.getText().toString() + "_Images", "array", getPackageName()))[1];
        } else if (view.getId() == R.id.recipeName3) {
            imageName = getResources().getStringArray(getResources().getIdentifier(categoryTitle.getText().toString() + "_Images", "array", getPackageName()))[2];
        }

        intent.putExtra("IMAGE_NAME", imageName);
        startActivity(intent);
    }

    public void goBack(View view) {

        finish();
    }




}