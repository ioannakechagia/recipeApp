package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

public class RecipeActivity extends AppCompatActivity {
    ImageView recipeImage, video, link;
    TextView recipeName, recipeCategory, recipeTime, recipeIngredients, recipeSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recipeImage=findViewById(R.id.imageRecipe);
        video=findViewById(R.id.video);
        link=findViewById(R.id.link);
        recipeName=findViewById(R.id.recipeName);
        recipeTime=findViewById(R.id.recipeTime);
        recipeIngredients=findViewById(R.id.recipeIngredients);
        recipeSteps=findViewById(R.id.recipeSteps);

        recipeCategory = findViewById(R.id.recipeCategory);
        Intent intent = getIntent();
        String receivedValue = intent.getStringExtra("CATEGORY_NAME");
        recipeCategory.setText(receivedValue);

        String imageName = intent.getStringExtra("IMAGE_NAME") +"r";

        ImageView recipeImage = findViewById(R.id.imageRecipe);
        int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        recipeImage.setImageResource(imageId);


        recipeTime = findViewById(R.id.recipeTime);
        String receivedRecipeNameLower = intent.getStringExtra("RECIPE_NAME").toLowerCase();

        String receivedRecipeName2 = receivedRecipeNameLower +"_name";
        int nameId = getResources().getIdentifier(receivedRecipeName2, "string", getPackageName());
        recipeName.setText(nameId);

        String receivedRecipeTime = receivedRecipeNameLower +"_time";
        int timeId = getResources().getIdentifier(receivedRecipeTime, "string", getPackageName());
        recipeTime.setText(timeId);

        recipeIngredients = findViewById(R.id.recipeIngredients);
        String receivedRecipeIngredients = receivedRecipeNameLower +"_ingredients";
        int ingredientsId = getResources().getIdentifier(receivedRecipeIngredients, "string", getPackageName());
        recipeIngredients.setText(ingredientsId);

        recipeSteps = findViewById(R.id.recipeSteps);
        String receivedRecipeSteps = receivedRecipeNameLower +"_steps";
        int stepsId = getResources().getIdentifier(receivedRecipeSteps, "string", getPackageName());
        recipeSteps.setText(stepsId);

        String url = receivedRecipeNameLower +"_link";
        int urlId = getResources().getIdentifier(url, "string", getPackageName());

        link.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String urlString = getString(urlId);
                gotoUrl(urlString);
            }

        });

        String videoUrl = receivedRecipeNameLower +"_youtube";
        int videoUrlId = getResources().getIdentifier(videoUrl, "string", getPackageName());

        video.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String videoUrlString = getString(videoUrlId);
                gotoUrl(videoUrlString);
            }

        });





    }

    private void gotoUrl(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }


    public void goBack(View view) {
        finish();
    }
}