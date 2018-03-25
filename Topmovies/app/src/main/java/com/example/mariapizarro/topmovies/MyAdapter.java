package com.example.mariapizarro.topmovies;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    ArrayList<Item> movieList = new ArrayList<>();
    Context context;

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        movieList = objects;
    }

    @Override
    public int getCount() {
        System.out.println("cuenta"+ super.getCount());
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("estooo");
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);

        TextView textView = v.findViewById(R.id.textView);
        TextView textView1 = v.findViewById(R.id.textView2);
        TextView textView2 = v.findViewById(R.id.textView3);
        ImageView imageView = v.findViewById(R.id.imageView);
        ImageView imageView1 = v.findViewById(R.id.imageView2);


        textView.setText(movieList.get(position).getmovieName());
        textView2.setText(movieList.get(position).getmovieStars());
        textView1.setText("Metascore: " + movieList.get(position).getmovieMetascore());
        imageView.setImageDrawable(movieList.get(position).getmovieImage());
        imageView1.setImageDrawable(ContextCompat.getDrawable(context,android.R.drawable.star_big_on));


        return v;

    }

}