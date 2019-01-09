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

public class MenuItemsRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    /**
     * This is done in the same way as the categoriesRequest Activity
     */

    Context context;
    Callback activity;
    private static String API = "https://resto.mprog.nl/";
    private String endpointName = "menu";

    public MenuItemsRequest(Context context) {
        this.context = context;
    }

    public interface Callback {
        void gotMenuItems(ArrayList<MenuItem> menuItems);
        void gotMenuItemsError(String message);
    }

    public void getMenuItems(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(this.context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(API + endpointName,
                null, this, this);
        queue.add(jsonObjectRequest);
        this.activity = activity;
    }

    @Override
    public void onResponse(JSONObject response) {
        ArrayList<MenuItem> takenMenu = new ArrayList<MenuItem>();
        try {
            JSONArray menuList = response.getJSONArray("items");
            for (int i = 0; i < menuList.length(); i++) {
                JSONObject item = menuList.getJSONObject(i);
                MenuItem menuClass = new MenuItem(item.getString("name"),
                        item.getString("description"), item.getString("image_url"),
                        item.getInt("price"), item.getString("category"));
                takenMenu.add(menuClass);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.activity.gotMenuItems(takenMenu);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        this.activity.gotMenuItemsError(error.getMessage());
    }
}
