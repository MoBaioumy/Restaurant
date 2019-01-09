package com.example.moham.resturant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;


public class MenuActivity extends AppCompatActivity implements MenuItemsRequest.Callback {

    String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MenuItemsRequest APIrequest = new MenuItemsRequest(this);
        APIrequest.getMenuItems(this);

        // get data about the category
        Intent intent = getIntent();
        categoryName = intent.getStringExtra("category");
    }

    @Override
    public void gotMenuItems(ArrayList<MenuItem> menuItems) {
        // create listView and link to the adapter to display all the items in the list
        ArrayList<MenuItem> clickedItems = this.getClickedCategories(menuItems);
        MenuItemAdapter adapter = new MenuItemAdapter(this,
                R.layout.entry_menu_item,clickedItems);
        ListView listView = findViewById(R.id.listViewMenu);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new listViewListener());
    }

    @Override
    public void gotMenuItemsError(String message) {
        // print error message to the user
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private ArrayList<MenuItem> getClickedCategories(ArrayList<MenuItem> menuItem) {
        // filter all the categories for what you clicked
        Iterator iterator = menuItem.iterator();
        while (iterator.hasNext())
        {
            MenuItem item = (MenuItem) iterator.next();
            if (!item.getCategory().equals(this.categoryName))
                iterator.remove();
        }
        return menuItem;
    }

    private class listViewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), MenuItemActivity.class);
            MenuItem item = (MenuItem) parent.getItemAtPosition(position);

            // pass in the appropriate data for the detailed activity
            intent.putExtra("name", item.getName());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("image", item.getImageUrl());
            intent.putExtra("price", String.valueOf(item.getPrice()));
            startActivity(intent);
        }
    }

}