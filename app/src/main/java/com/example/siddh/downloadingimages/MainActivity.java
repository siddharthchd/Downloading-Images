package com.example.siddh.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloadedImg;

    public void downloadImage(View view) {

        //https://s-media-cache-ak0.pinimg.com/736x/5d/a0/8d/5da08d24bc4c7d2847ee5dfa1604b114--naruto-shippudden-naruto-pics.jpg
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {

            myImage = task.execute("https://s-media-cache-ak0.pinimg.com/736x/5d/a0/8d/5da08d24bc4c7d2847ee5dfa1604b114--naruto-shippudden-naruto-pics.jpg").get();
            downloadedImg.setImageBitmap(myImage);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Download Image", "Button Tapped");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        downloadedImg = (ImageView) findViewById(R.id.imageView);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }
    }
}
