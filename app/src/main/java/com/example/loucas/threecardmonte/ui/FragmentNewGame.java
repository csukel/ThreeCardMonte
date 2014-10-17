package com.example.loucas.threecardmonte.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.loucas.threecardmonte.R;
import com.example.loucas.threecardmonte.core.Player;
import com.example.loucas.threecardmonte.database.PlayerProvider;

import java.io.Serializable;

/**
 * Created by loucas on 05/10/2014.
 */
public class FragmentNewGame extends Fragment {

    private int position = -1;
    private Toast m_currentToast = null;

    public FragmentNewGame() {
    }

    private EditText edtNickname = null;
    private EditText edtPass = null;
    private Button btnPlay = null;
    private Button btnNewPlayer = null;
    private Context fragmentContext = null;
    private PlayerProvider playerProvider = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_new_game, container, false);
        this.position = getArguments().getInt("Position");
        fragmentContext = container.getContext();
        initializeUI(rootView);

        playerProvider = new PlayerProvider(fragmentContext);

        buttonPlayClicked();
        buttonNewPlayerClicked();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        playerProvider.closeConnection();
    }

    private void buttonNewPlayerClicked() {
        btnNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pop up a user registration dialog box
                showRegistrationDialogBox();
            }
        });
    }

    private void showRegistrationDialogBox() {
        //prepare a linear layout for the dialog box
        Context context = fragmentContext;
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        //create an alert dialog box builder to set content
        AlertDialog.Builder builder = new AlertDialog.Builder(fragmentContext);
        builder.setTitle("Please fill your details below!");
        //nickname edit text
        final EditText nickReg = new EditText(fragmentContext);
        nickReg.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        nickReg.setHint("Nickname");
        layout.addView(nickReg);
        //password input box
        final EditText passwordReg = new EditText(fragmentContext);
        passwordReg.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordReg.setHint("Password");
        layout.addView(passwordReg);
        //confirm password input box
        final EditText cpasswordReg = new EditText(fragmentContext);
        cpasswordReg.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        cpasswordReg.setHint("Confirm Password");
        layout.addView(cpasswordReg);

        //set view for the dialog box
        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        final AlertDialog dialogReg = builder.create();
        dialogReg.show();
        dialogReg.getButton(dialogReg.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (nickReg.getText().toString().equals("") | passwordReg.getText().toString().equals("")
                        | cpasswordReg.getText().toString().equals("")) {
                    showToast("Please fill all the fields");
                } else if (!passwordReg.getText().toString().equals(cpasswordReg.getText().toString())) {
                    showToast("Please enter your password again. Password does not match");
                    passwordReg.setText("");
                    cpasswordReg.setText("");
                    passwordReg.requestFocus();
                } else if (nickReg.getText().toString().contains("/")) {
                    showToast("Nickname cannot contain the special character \"/\" ");
                    nickReg.setText("");
                    nickReg.requestFocus();
                } else if (playerProvider.isNicknameReserved(nickReg.getText().toString())) {
                    showToast("The nickname is reserved. Please choose another one.");
                } else {
                    playerProvider.addPlayerInfo(nickReg.getText().toString(), passwordReg.getText().toString());
                    showToast("Registration Successful!");
                    dialogReg.dismiss();
                }

            }

        });
    }

    //when button play is clicke do ...
    private void buttonPlayClicked() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noNick() || noPass()) {
                    showDialogMsg();
                } else if (playerProvider.isPlayerAuthCorrect(edtNickname.getText().toString(), edtPass.getText().toString())) {
                    Intent intentGame = new Intent(fragmentContext, MainGame.class);
                    intentGame.putExtra("Nickname", edtNickname.getText().toString());
                    intentGame.putExtra("Password", edtPass.getText().toString());
                    edtNickname.setText("");
                    edtPass.setText("");
                    startActivity(intentGame);
                } else {
                    showToast("No match found");
                }
            }
        });
    }

    private boolean noPass() {
        if (edtPass.getText().toString().isEmpty()) {
            return true;
        } else
            return false;
    }

    private void showDialogMsg() {
        new AlertDialog.Builder(fragmentContext)
                .setTitle("Error Continuing")
                .setMessage("Please type your nickname")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private boolean noNick() {
        if (edtNickname.getText().toString().isEmpty()) {
            return true;
        } else
            return false;
    }

    private void initializeUI(View root) {
        edtNickname = (EditText) root.findViewById(R.id.edtNickname);
        edtPass = (EditText) root.findViewById(R.id.edtPassword);
        btnPlay = (Button) root.findViewById(R.id.btnPlay);
        btnNewPlayer = (Button) root.findViewById(R.id.btnRegister);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainMenu) activity).onSectionAttached(position);
    }

    private void showToast(String text) {
        if (m_currentToast != null) {
            m_currentToast.cancel();
        }
        m_currentToast = Toast.makeText(fragmentContext, text, Toast.LENGTH_SHORT);
        m_currentToast.show();
    }
}
