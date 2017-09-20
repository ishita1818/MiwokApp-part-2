package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private  int mcolourid;
    public WordAdapter(Context context, ArrayList<Word> pWords,int colourid){
        super(context, 0, pWords);
        mcolourid=colourid;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {


        View listItemView = convertView;
        //check if current view is being used otherwise inflate the view

        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

        Word local_word = (Word) getItem(position);
        LinearLayout linear =(LinearLayout)listItemView.findViewById(R.id.linear_layout);
        int colour= ContextCompat.getColor(getContext(),mcolourid);
        linear.setBackgroundColor(colour);

        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.text_1_view);
        miwokTextView.setText( local_word.getMiwok());

        TextView defaultTextView =(TextView)listItemView.findViewById(R.id.text_2_view);
        defaultTextView.setText(local_word.getDefaultWord());


        ImageView imageView = (ImageView)listItemView.findViewById(R.id.image_view);
        if(local_word.hasImage()) {
            imageView.setImageResource(local_word.getImageResourceid());
            imageView.setVisibility(View.VISIBLE);
        }
        else
            imageView.setVisibility(View.GONE);




       return listItemView;

    }
}
