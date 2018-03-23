package com.example.mariapizarro.topmovies;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //cargarDatos();
        new RetrieveFeedTask().execute("http://www.imdb.com/list/ls064079588/");
        grid = findViewById(R.id.grid);



    }

    public void cargarDatos(){

        try {
            Document doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();
            Log.i("titulo", doc.title());
            Elements titulos = doc.select("div .lister-item mode-detailed .lister-item-content h3 .lister-item-header a");
            Log.i("titulos", titulos.toString());
        }
        catch (IOException e){}

    }


    class RetrieveFeedTask extends AsyncTask<String, Void, Void>{

        private Exception exception;

        protected Void doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                Document doc = Jsoup.connect("http://www.imdb.com/list/ls064079588/").get();

                Elements imagenes = doc.select("div.lister-item.mode-detail div.lister-item-image.ribbonize a img");

                String link = imagenes.attr("abs:loadlate");
                Log.i("titulo", doc.title());
                Elements titulos = doc.select("div.lister-item-content h3.lister-item-header a");
                Elements estrellas = doc.select("div.lister-item-content div.ratings-bar div.inline-block.ratings-imdb-rating strong");
                Log.i("estrellas", estrellas.html().toString());
                Elements metascores = doc.select("div.lister-item-content div.ratings-bar div.inline-block.ratings-metascore span.metascore");
                Log.i("metascores", metascores.html().toString());
                Log.i("imagenes", link);


                ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.ListView,R.id.textView,StringArray);




            } catch (Exception e) {
                this.exception = e;

            }


            return null;
        }


    }


}
