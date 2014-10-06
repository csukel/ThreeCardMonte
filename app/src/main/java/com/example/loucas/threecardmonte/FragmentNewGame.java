package com.example.loucas.threecardmonte;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by loucas on 05/10/2014.
 */
public class FragmentNewGame extends Fragment {

    private int position = -1;
    public FragmentNewGame (){}

    private EditText edtNickname = null;
    private Button btnPlay = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
        this.position = getArguments().getInt("Position");
        initializeUI(rootView);
        buttonPlayClicked();

        return rootView;
    }

    private void buttonPlayClicked() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noNick()) {
                    // Log.i("Fragment New Game","No nick" );
                    showDialogMsg();
                } else {
                    //Log.i("Fragment New Game",edtNickname.getText().toString());
                    //TODO: jump to game activity, put nickname on the intent to pass to the next activity
                }
            }
        });
    }

    private void showDialogMsg() {
        //TODO: show an alert dialog
    }

    private boolean noNick() {
        if (edtNickname.getText().toString().isEmpty()) {
            return true;
        } else
            return false;
    }

    private void initializeUI(View root) {
        edtNickname = (EditText) root.findViewById(R.id.edtNickname);
        btnPlay = (Button) root.findViewById(R.id.btnPlay);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainMenu) activity).onSectionAttached(position);
    }
}
