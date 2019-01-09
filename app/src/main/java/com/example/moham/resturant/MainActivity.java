package com.example.moham.resturant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoriesRequest.Callback {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // make API request
        CategoriesRequest APIrequest = new CategoriesRequest(this);
        APIrequest.getCategories(this);

    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.entry_category_item, categories);

        // create listView and link to the adapter to display all the items in the list
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new listViewListener());
    }

    @Override
    public void gotCategoriesError(String message) {
        // in case of error message from CategoriesRequest, Display and error for the user
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private class listViewListener implements AdapterView.OnItemClickListener {
        @Override
        // move the right activity when clicking on an item
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            String categoryName = (String) parent.getItemAtPosition(position);
            intent.putExtra("category", categoryName);
            startActivity(intent);
        }
    }

}