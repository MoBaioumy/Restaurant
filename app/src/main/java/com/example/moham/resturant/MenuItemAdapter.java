package com.example.moham.resturant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuItemAdapter extends ArrayAdapter<MenuItem> {

    ArrayList<MenuItem> items;
    private static class ViewHolder {
        TextView textName;
        TextView textPrice;
        ImageView image;
    }

    /**
     * Default
     * @param context
     * @param resource
     * @param objects
     */
    public MenuItemAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuItem item = getItem(position);

        // Inflate the view in case of an existing one is being refused
        ViewHolder viewHolder;
        final View result;

        // create the ViewHolder
        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.entry_menu_item, parent, false);

        // get values from the views
        viewHolder.textName = convertView.findViewById(R.id.textViewName);
        viewHolder.textPrice = convertView.findViewById(R.id.textViewPrice);
        viewHolder.image = convertView.findViewById(R.id.imageViewMenu);

        // set the result to the inflated view
        result = convertView;
        convertView.setTag(viewHolder);

        // set the text values
        viewHolder.textName.setText(item.getName());
        String price = String.valueOf(item.getPrice()) + " €";
        viewHolder.textPrice.setText(price);

        // Load the images as image view
        String pathToFile = item.getImageUrl();
        URLToImage downloadTask = new URLToImage(viewHolder.image);
        downloadTask.execute(pathToFile);

        return result;
    }
}