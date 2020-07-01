package com.amol.tictactoe;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.R.color;
import android.graphics.ColorMatrix;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.XMLFormatter;

import static java.lang.Boolean.FALSE;

public class MainActivity extends AppCompatActivity {

    final String TAG = "TicTacToe";
    Boolean TwoPlayer = true;
    public int PlayerNunmber=1;  //else player number will be 2
    ArrayList<Integer> PLayer1 = new ArrayList<Integer>(); //to store player 1 moves
    ArrayList<Integer> PLayer2 = new ArrayList<Integer>(); //to store player 2 moves

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view) {
        Button buttonSelected = (Button) view;
        int CellID=0;
        switch (buttonSelected.getId()){
            case R.id.button1:
                CellID=1;
                break;
            case R.id.button2:
                CellID=2;
                break;

            case R.id.button3:
                CellID=3;
                break;

            case R.id.button4:
                CellID=4;
                break;

            case R.id.button5:
                CellID=5;
                break;

            case R.id.button6:
                CellID=6;
                break;

            case R.id.button7:
                CellID=7;
                break;

            case R.id.button8:
                CellID=8;
                break;

            case R.id.button9:
                CellID=9;
                break;
            case R.id.buttonReset:
                ResetGame();
                break;
            case R.id.switchButton:
                TwoPlayer=false;
                break;
        }
        if(CellID>=1&&CellID<=9)
            playGame(CellID,buttonSelected);
    }

    public void playGame(int CellId, Button button) {
        Log.d(TAG, "Player " + CellId);
        if (PlayerNunmber == 1) {
            PlayerNunmber = 2;
            PLayer1.add(CellId);
            button.setText("X");
//            button.setBackgroundColor(Color.MAGENTA);
            button.setBackgroundColor(0xFF00BCD4);
            //AutoPlay with device
            if (!TwoPlayer)
                AutoPlay();
        } else if (PlayerNunmber == 2) {
            PlayerNunmber = 1;
            PLayer2.add(CellId);
            button.setText("O");
//            button.setBackgroundColor(Color.CYAN);
            button.setBackgroundColor(0xFFFF9800);
        }
        button.setEnabled(FALSE);
        checkWinner();
    }

    public void checkWinner(){
        int Winner=-1;
        //Row 1
        if(PLayer1.contains(1) && PLayer1.contains(2) && PLayer1.contains(3))
            Winner=1;
        if(PLayer2.contains(1) && PLayer2.contains(2) && PLayer2.contains(3))
            Winner=2;
        //Row 2
        if(PLayer1.contains(4) && PLayer1.contains(5) && PLayer1.contains(6))
            Winner=1;
        if(PLayer2.contains(4) && PLayer2.contains(5) && PLayer2.contains(6))
            Winner=2;
        //Row3
        if(PLayer1.contains(7) && PLayer1.contains(8) && PLayer1.contains(9))
            Winner=1;
        if(PLayer2.contains(7) && PLayer2.contains(8) && PLayer2.contains(9))
            Winner=2;
        //Column 1
        if(PLayer1.contains(1) && PLayer1.contains(4) && PLayer1.contains(7))
            Winner=1;
        if(PLayer2.contains(1) && PLayer2.contains(4) && PLayer2.contains(7))
            Winner=2;
        //Column 2
        if(PLayer1.contains(2) && PLayer1.contains(5) && PLayer1.contains(8))
            Winner=1;
        if(PLayer2.contains(2) && PLayer2.contains(5) && PLayer2.contains(8))
            Winner=2;
        //Column 3
        if(PLayer1.contains(3) && PLayer1.contains(6) && PLayer1.contains(9))
            Winner=1;
        if(PLayer2.contains(3) && PLayer2.contains(6) && PLayer2.contains(9))
            Winner=2;
        //Across 1
        if(PLayer1.contains(1) && PLayer1.contains(5) && PLayer1.contains(9))
            Winner=1;
        if(PLayer2.contains(1) && PLayer2.contains(5) && PLayer2.contains(9))
            Winner=2;
        //Across 2
        if(PLayer1.contains(3) && PLayer1.contains(5) && PLayer1.contains(7))
            Winner=1;
        if(PLayer2.contains(3) && PLayer2.contains(5) && PLayer2.contains(7))
            Winner=2;
        //In case we have a winner
        if(Winner!=-1){
            if(Winner==1) {
                LockCells();
                TwoPlayer = true;
                Toast.makeText(this, "Player 1 Wins!!!", Toast.LENGTH_LONG).show();
            } else if (Winner==2) {
                LockCells();
                TwoPlayer = true;
                Toast.makeText(this, "Player 2 Wins!!!", Toast.LENGTH_LONG).show();
            }
        }
    }

    //Autoplay
    void AutoPlay(){
        ArrayList<Integer> EmptyCells = new ArrayList<Integer>();
        for (int CellId=1;CellId<10;CellId++){
            if (!(PLayer1.contains(CellId)||PLayer2.contains(CellId))){
                EmptyCells.add(CellId);
            }
        }
        Random r = new Random();
        int RandomIndex=r.nextInt(EmptyCells.size()-0)+0; //random_number= r.nextint (max-min)-min
        int autoMove=EmptyCells.get(RandomIndex);

        Button buttonSelected;
        switch (autoMove){
            case 1:
                buttonSelected = findViewById(R.id.button1);
                break;

            case 2:
                buttonSelected = findViewById(R.id.button2);
                break;

            case 3:
                buttonSelected = findViewById(R.id.button3);
                break;

            case 4:
                buttonSelected = findViewById(R.id.button4);
                break;

            case 5:
                buttonSelected = findViewById(R.id.button5);
                break;

            case 6:
                buttonSelected = findViewById(R.id.button6);
                break;

            case 7:
                buttonSelected = findViewById(R.id.button7);
                break;

            case 8:
                buttonSelected = findViewById(R.id.button8);
                break;

            case 9:
                buttonSelected = findViewById(R.id.button9);
                break;
            default:
                buttonSelected = findViewById(R.id.button9);
        }
        playGame(autoMove,buttonSelected);
    }

    void ResetGame(){
        Button button;
        button = findViewById(R.id.button1);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button2);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button3);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button4);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button5);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button6);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button7);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button8);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        button = findViewById(R.id.button9);
        button.setEnabled(true);
        button.setText("");
        button.setBackgroundColor(Color.LTGRAY);

        Switch playerSwitch = (Switch)findViewById(R.id.switchButton);
        playerSwitch.setEnabled(true);
        playerSwitch.setChecked(false);

        TwoPlayer = true;
        PlayerNunmber = 1;  //else player number will be 2
        PLayer1.removeAll(PLayer1);
        PLayer2.removeAll(PLayer2);
    }

    void LockCells(){
        findViewById(R.id.button1).setEnabled(false);
        findViewById(R.id.button2).setEnabled(false);
        findViewById(R.id.button3).setEnabled(false);
        findViewById(R.id.button4).setEnabled(false);
        findViewById(R.id.button5).setEnabled(false);
        findViewById(R.id.button6).setEnabled(false);
        findViewById(R.id.button7).setEnabled(false);
        findViewById(R.id.button8).setEnabled(false);
        findViewById(R.id.button9).setEnabled(false);
    }
}