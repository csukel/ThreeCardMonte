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
public class FragmentHelp extends Fragment {

    private int position = -1;

    public FragmentHelp() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        this.position = getArguments().getInt("Position");


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainMenu) activity).onSectionAttached(position);
    }
}
