package com.example.moham.resturant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        // get intent and all the values passed with it
        Intent intent = getIntent();

        TextView textName = findViewById(R.id.ItemName);
        ImageView imageItem = findViewById(R.id.imageView);
        TextView textDescription = findViewById(R.id.ItemDescription);
        TextView textPrice = findViewById(R.id.ItemPrice);

        // set the appropriate text in all fields as passed
        textName.setText(intent.getStringExtra("name"));
        textDescription.setText(intent.getStringExtra("description"));
        String price = intent.getStringExtra("price") + " €";
        textPrice.setText(price);

        // get the image
        URLToImage downloadTask = new URLToImage(imageItem);
        downloadTask.execute(intent.getStringExtra("image"));

    }
}