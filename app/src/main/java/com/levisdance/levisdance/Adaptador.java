package com.levisdance.levisdance;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edward on 17/09/17.
 */

public class Adaptador extends ArrayAdapter<Publicacion> {

   // private static final R = ;
    private Context context;
    private List<Publicacion> publicaciones;

    //Constructor
    public Adaptador(Context _context,int resource, ArrayList<Publicacion> _imageList) {

        super(_context, resource, _imageList);
        context = _context;
        publicaciones = _imageList;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        //get the image we are displaying
        Publicacion publi = publicaciones.get(position);

        //get the inflater and inflate the XML laysout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //conditionally inflate either standard or special template
        View view;
        view = inflater.inflate(R.layout.item, null);

        //Conect Code with layout objects

        ImageView imgLoad = (ImageView) view.findViewById(R.id.imageView);
        TextView tittleLoad = (TextView) view.findViewById(R.id.item_name);
        TextView dateLoad = (TextView) view.findViewById(R.id.item_time);
        TextView ownerLoad = (TextView) view.findViewById(R.id.item_user_name);
        TextView locationLoad = (TextView) view.findViewById(R.id.item_location);

/*
        //Receive Image path
        File imgFile = new  File(publicaciones.get(position).getImage());

        //Check that the file exists
        if(imgFile.exists()){

            //Set the image to a bitmap
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            //Assing the bitmap to the imageView
            //imgLoad.setImageResource(myBitmap);
            imgLoad.setImageBitmap(myBitmap);
        }*/

        //Set the rest of the texts
        imgLoad.setImageDrawable(publicaciones.get(position).getImage());
        tittleLoad.setText(publicaciones.get(position).getTitle());
        dateLoad.setText(publicaciones.get(position).getTime());
        ownerLoad.setText(publicaciones.get(position).getUsername());
        locationLoad.setText(publicaciones.get(position).getLocation());

        return view;
    }


}