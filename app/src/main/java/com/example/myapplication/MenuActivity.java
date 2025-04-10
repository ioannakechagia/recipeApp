package com.example.myapplication;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class MenuActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView =findViewById(R.id.nameTextView);

        Intent receiverIntent = getIntent();
        String receivedValue = receiverIntent.getStringExtra("NAME");
        textView.setText(receivedValue);

    }


    public void selectCategory(View view){
        String category = ((TextView) view).getText().toString();
        Intent intent = new Intent(MenuActivity.this, CategoryRecipesActivity.class);
        intent.putExtra("CATEGORY_NAME", category);
        startActivity(intent);
    }
}