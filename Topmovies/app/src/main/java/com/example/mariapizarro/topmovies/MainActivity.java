package com.example.mariapizarro.topmovies;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView grid;
    ArrayList<Item> movieList = new ArrayList<>();
    ArrayList movieName, movieImage = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);


        new CargarDatos().execute("http://www.imdb.com/list/ls064079588/");










    }


    public static Drawable descargarImagen(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "");
            return d;
        } catch (Exception e) {
            return null;
        }
    }


    class CargarDatos extends AsyncTask<String, Void, Void>{

        private Exception exception;

        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                Document doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();

                Elements imagenes = doc.select("div.lister-item.mode-detail div.lister-item-image.ribbonize a img.loadlate");
                Elements titulos = doc.select("div.lister-item-content h3.lister-item-header a");
                Elements estrellas = doc.select("div.lister-item-content div.ratings-bar div.inline-block.ratings-imdb-rating strong");
                Elements metascores = doc.select("div.lister-item-content div.ratings-bar div.inline-block.ratings-metascore span.metascore");



                for(int i=0;i<imagenes.size();i++)
                {
                    movieImage.add(descargarImagen(imagenes.get(i).attr("loadlate")));
                }



                String[] title = (titulos.html()).split("\n");
                String[] stars = (estrellas.html()).split("\n");
                String[] metadata = (metascores.html()).split("\n");


                for(int i = 0;i<20;i++){
                    movieList.add(new Item(title[i],(Drawable)movieImage.get(i),metadata[i],stars[i]));
                }





            } catch (Exception e) {
                this.exception = e;

            }


            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter = new MyAdapter(getApplicationContext(),R.layout.grid_view_items,movieList);
            ProgressBar progressBar = findViewById(R.id.progress);
            progressBar.setVisibility(View.INVISIBLE);
            grid.setAdapter(adapter);


        }


    }


}
