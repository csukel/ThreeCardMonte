package com.example.loucas.threecardmonte.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.loucas.threecardmonte.R;

import com.example.loucas.threecardmonte.core.GameInfo;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by loucas on 17/10/2014.
 */
public class ScoreAdapter extends ArrayAdapter<GameInfo> {

    private final Context context;
    private final ArrayList<GameInfo> values;

    public ScoreAdapter(Context context, ArrayList<GameInfo> values) {
        super(context, R.layout.score_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.score_list_item, parent, false);
        TextView txtNickname = (TextView) rowView.findViewById(R.id.txtSNickname);
        TextView txtDate = (TextView) rowView.findViewById(R.id.txtSDate);
        TextView txtScore = (TextView) rowView.findViewById(R.id.txtSScore);
        txtNickname.setText(values.get(position).getPlayerNick());
        txtDate.setText(values.get(position).getDatePlayed());
        txtScore.setText(Integer.toString(values.get(position).getDiff()));


        return rowView;
    }

}
