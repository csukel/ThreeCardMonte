package com.example.loucas.threecardmonte.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.loucas.threecardmonte.R;

import com.example.loucas.threecardmonte.core.GameInfo;

/**
 * Created by loucas on 17/10/2014.
 */
public class ScoreAdapter extends ArrayAdapter<GameInfo> {

    private final Context context;
    private final GameInfo[] values;

    public ScoreAdapter(Context context, GameInfo[] values) {
        super(context, R.layout.score_list_item, values);
        this.context = context;
        this.values = values;
    }

}
