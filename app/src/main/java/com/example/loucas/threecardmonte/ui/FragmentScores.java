package com.example.loucas.threecardmonte.ui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.loucas.threecardmonte.R;
import com.example.loucas.threecardmonte.core.GameInfo;
import com.example.loucas.threecardmonte.database.GameInfoProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by loucas on 05/10/2014.
 */
public class FragmentScores extends Fragment {

    private int position = -1;
    private GameInfoProvider gameInfoProvider = null;
    private ListView listView = null;
    public FragmentScores() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        this.position = getArguments().getInt("Position");
        //TextView txtLabel = (TextView) rootView.findViewById(R.id.section_label);
        gameInfoProvider = new GameInfoProvider(container.getContext());
        ArrayList<GameInfo> adapterList = gameInfoProvider.getGamesInfo();
        sortList(adapterList);
        listView = (ListView) rootView.findViewById(R.id.lstScores);
        ScoreAdapter scoreAdapter = new ScoreAdapter(container.getContext(), adapterList);
        listView.setAdapter(scoreAdapter);


        return rootView;
    }

    //sortList by overriding the compare method from the comparator to return objects
    //in a descending order
    private void sortList(ArrayList<GameInfo> adapterList) {
        Collections.sort(adapterList, new Comparator<GameInfo>() {
            @Override
            public int compare(GameInfo o1, GameInfo o2) {
                final int gameInfo1 = o1.getDiff();
                final int gameInfo2 = o2.getDiff();
                return gameInfo1 < gameInfo2 ? 1
                        : gameInfo1 > gameInfo2 ? -1 : 0;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gameInfoProvider.closeConnection();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainMenu) activity).onSectionAttached(position);
    }
}
