package com.example.mariapizarro.topmovies;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new CargarDatos().execute("http://www.imdb.com/list/ls064079588/");










    }


    public static Drawable descargarImagen(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
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

                String link = imagenes.attr("loadlate");
                Log.i("titulo", doc.title());
                Elements titulos = doc.select("div.lister-item-content h3.lister-item-header a");
                Elements estrellas = doc.select("div.lister-item-content div.ratings-bar div.inline-block.ratings-imdb-rating strong");
                //Log.i("estrellas", estrellas.html().toString());
                Elements metascores = doc.select("div.lister-item-content div.ratings-bar div.inline-block.ratings-metascore span.metascore");
                //Log.i("metascores", metascores.html().toString());
                Log.i("imagenes", imagenes.toString());


                for(int i=0;i<imagenes.size();i++)
                {
                    movieImage.add(descargarImagen(imagenes.get(i).attr("loadlate")));
                }



                grid = findViewById(R.id.grid);

                String tit = titulos.html();
                String img = link;
                String[] parts = tit.split("\n");

//                for (Element element : titulos) {
//                    movieName.add(element.ownText());
//                    Log.i("hola",element.child(0).toString());
//                    System.out.println("hola");
//                    System.out.println(element.toString());
//                }
//                for (Element element : imagenes) {
//                    movieImage.add(descargarImagen(element.ownText()));
//                }

                for(int i = 0;i<20;i++){
                    Log.i("hola", parts[i]);
                    movieList.add(new Item(parts[i],(Drawable)movieImage.get(i)));
                }

                MyAdapter myAdapter = new MyAdapter(getApplicationContext(),R.layout.grid_view_items,movieList);
                grid.setAdapter(myAdapter);
                Log.i("listo","listo");








            } catch (Exception e) {
                this.exception = e;

            }


            return null;
        }


    }


}
