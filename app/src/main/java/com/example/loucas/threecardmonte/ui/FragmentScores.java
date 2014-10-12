package com.example.loucas.threecardmonte.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loucas.threecardmonte.R;

/**
 * Created by loucas on 05/10/2014.
 */
public class FragmentScores extends Fragment {

    private int position = -1;

    public FragmentScores() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scores, container, false);
        this.position = getArguments().getInt("Position");
        //TextView txtLabel = (TextView) rootView.findViewById(R.id.section_label);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainMenu) activity).onSectionAttached(position);
    }
}
