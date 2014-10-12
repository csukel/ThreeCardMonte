package com.example.loucas.threecardmonte.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loucas.threecardmonte.R;
import com.example.loucas.threecardmonte.core.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by loucas on 08/10/2014.
 */
public class MainGame extends Activity {
    private ImageView cardLeft;
    private ImageView cardMiddle;
    private ImageView cardRight;
    private Button btnDraw;
    private Button btnContinue;
    private TextView txtWins;
    private TextView txtLosses;

    private Toast m_currentToast = null;
    private int cardSelected;
    private Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);
        String userNickname = getIntent().getStringExtra("Nickname");
        if (userNickname != null)
            this.getActionBar().setTitle("Hello, " + userNickname + "! Try to find the KING");
        initializeView();
    }

    //initialize the screen-map xml items to images and buttons
    private void initializeView() {
        cardLeft = (ImageView) findViewById(R.id.imgCardLeft);
        cardMiddle = (ImageView) findViewById(R.id.imgCardMiddle);
        cardRight = (ImageView) findViewById(R.id.imgCardRight);
        cardLeft.setOnTouchListener(mOnTouchListener);
        cardMiddle.setOnTouchListener(mOnTouchListener);
        cardRight.setOnTouchListener(mOnTouchListener);

        btnDraw = (Button) findViewById(R.id.btnDraw);
        btnDraw.setOnClickListener(btnDrawClickListener);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(btnContinueClickListener);
        btnContinue.setVisibility(View.INVISIBLE);

        txtWins = (TextView) findViewById(R.id.txtWins);
        txtLosses = (TextView) findViewById(R.id.txtLosses);
    }

    View.OnClickListener btnDrawClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //first check if none of the images is touched
            if (noneCardTouched()) {
                //show a toast message
                showToast("You should select a card!");
            } else {
                //return back a number according to which card is selected, 0 left , 1 middle , 2 right
                int position = getCardSelected();
                if (game.getCardPack().get(position).getCardFace().equals("king")) {
                    // showToast("Congrats!!You won");
                    showCardsToUser(position, true);
                    game.gameWon();
                } else {
                    // showToast("You lost");
                    showCardsToUser(position, false);
                    game.gameLost();
                }
                //TODO draw button
                btnContinue.setVisibility(View.VISIBLE);
                btnDraw.setVisibility(View.INVISIBLE);
                updateScoreBoard();
            }
        }
    };

    private void updateScoreBoard() {
        txtWins.setText(Integer.toString(game.getWins()));
        txtLosses.setText(Integer.toString(game.getLosses()));
    }

    View.OnClickListener btnContinueClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //TODO continue button
            btnDraw.setVisibility(View.VISIBLE);
            btnContinue.setVisibility(View.INVISIBLE);
            game.startNewGame();
            initializeCardsBackground();
            initializeCardsImage();
        }
    };

    private void initializeCardsImage() {
        Drawable image = getResources().getDrawable(R.drawable.backsidecard);
        cardLeft.setImageDrawable(image);
        cardMiddle.setImageDrawable(image);
        cardRight.setImageDrawable(image);
    }

    private void initializeCardsBackground() {
        cardLeft.setBackgroundColor(Color.BLACK);
        cardMiddle.setBackgroundColor(Color.BLACK);
        cardRight.setBackgroundColor(Color.BLACK);
    }

    //when a card is selected and the player pressed the draw button do show the cards
    private void showCardsToUser(int position, boolean win) {
        if (!win) {
            switch (position) {

                case 0:
                    cardLeft.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    cardMiddle.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    cardRight.setBackgroundColor(Color.RED);
                    break;
            }
        }
        for (int i = 0; i < game.getCardPack().size(); i++) {
            Drawable image = null;
            if (game.getCardPack().get(i).getCardFace().equals("king"))
                image = getResources().getDrawable(R.drawable.king_of_clubs2);
            else if (game.getCardPack().get(i).getCardFace().equals("queen"))
                image = getResources().getDrawable(R.drawable.queen_of_clubs2);
            else
                image = getResources().getDrawable(R.drawable.jack_of_clubs2);
            switch (i) {
                case 0:
                    cardLeft.setImageDrawable(image);
                    break;
                case 1:
                    cardMiddle.setImageDrawable(image);
                    break;
                case 2:
                    cardRight.setImageDrawable(image);
                    break;
            }
        }

    }

    private void showToast(String text) {
        if (m_currentToast != null) {
            m_currentToast.cancel();
        }
        m_currentToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        m_currentToast.show();
    }

    private boolean noneCardTouched() {
        if (((ColorDrawable) cardLeft.getBackground()).getColor() == (Color.GREEN) ||
                ((ColorDrawable) cardMiddle.getBackground()).getColor() == (Color.GREEN) ||
                ((ColorDrawable) cardRight.getBackground()).getColor() == (Color.GREEN))
            return false;
        else
            return true;
    }

    View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View arg0, MotionEvent arg1) {
            if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
                switch (arg0.getId()) {
                    case R.id.imgCardLeft: // Id of the button
                        touchCardLeftAction();
                        break;
                    case R.id.imgCardMiddle: // Id of the button
                        touchCardMiddleAction();
                        break;
                    case R.id.imgCardRight: // Id of the button
                        touchCardRightAction();
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
    };

    private void touchCardRightAction() {
        if (((ColorDrawable) cardLeft.getBackground()).getColor() == (Color.GREEN) ||
                ((ColorDrawable) cardMiddle.getBackground()).getColor() == (Color.GREEN)) {
            cardLeft.setBackgroundColor(Color.BLACK);
            cardMiddle.setBackgroundColor(Color.BLACK);
        }

        cardRight.setBackgroundColor(Color.GREEN);
    }

    private void touchCardMiddleAction() {
        if (((ColorDrawable) cardLeft.getBackground()).getColor() == (Color.GREEN) ||
                ((ColorDrawable) cardRight.getBackground()).getColor() == (Color.GREEN)) {
            cardLeft.setBackgroundColor(Color.BLACK);
            cardRight.setBackgroundColor(Color.BLACK);
        }
        cardMiddle.setBackgroundColor(Color.GREEN);
    }

    private void touchCardLeftAction() {
        if (((ColorDrawable) cardRight.getBackground()).getColor() == (Color.GREEN) ||
                ((ColorDrawable) cardMiddle.getBackground()).getColor() == (Color.GREEN)) {
            cardRight.setBackgroundColor(Color.BLACK);
            cardMiddle.setBackgroundColor(Color.BLACK);
        }
        cardLeft.setBackgroundColor(Color.GREEN);
    }

    private int getCardSelected() {
        if (((ColorDrawable) cardLeft.getBackground()).getColor() == (Color.GREEN)) {
            return 0;
        } else if (((ColorDrawable) cardMiddle.getBackground()).getColor() == (Color.GREEN)) {
            return 1;
        } else return 2;

    }
}
