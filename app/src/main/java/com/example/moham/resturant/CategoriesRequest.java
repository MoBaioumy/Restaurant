package com.example.moham.resturant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;
    private String endpointName = "categories";

    // Static since the link is not going to change
    private static String API = "https://resto.mprog.nl/";


    // Default constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // call the functionality
    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public void getCategories(Callback activity) {
        // request the categories from the defined link which are formatted as JSON
        RequestQueue queue = Volley.newRequestQueue(this.context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(API + endpointName,
                null, this, this);

        queue.add(jsonObjectRequest);
        this.activity = activity;
    }

    // in case of successful response
    @Override
    public void onResponse(JSONObject response) {
        ArrayList<String> takenCategory = new ArrayList<String>();
        try {
            // request data at endpoint "/categories"
            JSONArray categoriesArray = response.getJSONArray(endpointName);

            // append all the elements to extractedCategory
            for (int i = 0; i < categoriesArray.length(); i++) {
                String category = categoriesArray.getString(i);
                takenCategory.add(category);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.activity.gotCategories(takenCategory);
    }

    // in case of of unsuccessful response get the the error message
    @Override
    public void onErrorResponse(VolleyError error) {
        //
        this.activity.gotCategoriesError(error.getMessage());
    }
}