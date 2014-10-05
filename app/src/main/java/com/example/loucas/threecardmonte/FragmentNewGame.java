package com.example.loucas.threecardmonte;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by loucas on 05/10/2014.
 */
public class FragmentNewGame extends Fragment {
    private int position = -1;
    public FragmentNewGame (){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
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
