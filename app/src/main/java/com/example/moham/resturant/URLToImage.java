package com.example.moham.resturant;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

class URLToImage extends AsyncTask<String, Void, Bitmap> {
    private ImageView bitmapImage;
    public URLToImage(ImageView bmImage) {
        this.bitmapImage = bmImage;
    }

    protected Bitmap doInBackground(String... url) {
        String pathToFile = url[0];
        Bitmap bitmap = null;
        try {
            InputStream in = new java.net.URL(pathToFile).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap result) {
        bitmapImage.setImageBitmap(result);
    }
}