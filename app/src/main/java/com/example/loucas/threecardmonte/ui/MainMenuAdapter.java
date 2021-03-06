package com.example.loucas.threecardmonte.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loucas.threecardmonte.R;

/**
 * Created by loucas on 04/10/2014.
 */
public class MainMenuAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MainMenuAdapter(Context context, String[] values) {
        super(context, R.layout.list_main_menu_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_main_menu_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.txtItemTitle);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgIcon);
        textView.setText(values[position]);
        // Change the icon depending on the item string
        String s = values[position];
        if (s.equals("Start New Game")) {
            imageView.setImageResource(R.drawable.backsidecard);
        } else if (s.equals("Scores")) {
            imageView.setImageResource(R.drawable.ranking);
        } else {
            imageView.setImageResource(R.drawable.help_icon);
        }

        return rowView;
    }
}
