package com.example.mariapizarro.topmovies;


import android.graphics.drawable.Drawable;

public class Item {

    String movieListName;
    Drawable movieListImage;

    public Item(String movieName,Drawable movieImage)
    {
        this.movieListImage = movieImage;
        this.movieListName = movieName;
    }
    public String getmovieName()
    {
        return movieListName;
    }
    public Drawable getmovieImage()
    {
        return movieListImage;
    }
}