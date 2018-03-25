package com.example.mariapizarro.topmovies;


import android.graphics.drawable.Drawable;

public class Item {

    String movieListName,movieListStars,movieListMetascore;
    Drawable movieListImage;


    public Item(String movieName,Drawable movieImage, String movieMetascore, String movieStars)
    {
        this.movieListImage = movieImage;
        this.movieListName = movieName;
        this.movieListMetascore = movieMetascore;
        this.movieListStars = movieStars;
    }
    public String getmovieName()
    {
        return movieListName;
    }
    public String getmovieStars()
    {
        return movieListStars;
    }
    public String getmovieMetascore()
    {
        return movieListMetascore;
    }
    public Drawable getmovieImage()
    {
        return movieListImage;
    }

}