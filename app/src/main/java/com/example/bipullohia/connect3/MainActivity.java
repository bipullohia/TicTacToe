package com.example.bipullohia.connect3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//playernumber: 0= red, 1=yellow

    int playernumber = 0;
    LinearLayout resultStatus;
    TextView matchstatus;
    Boolean isGameRunning = true, isWinnerAvail = false;

    int[] gamestatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] wincombnations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    // 2 means player hasn't played that position

    public void dropcounter(View view) {

        ImageView counter = (ImageView) view;
        resultStatus = (LinearLayout) findViewById(R.id.resultStatus);
        matchstatus = (TextView) findViewById(R.id.matchstatus);

        int tag = Integer.parseInt(counter.getTag().toString());

        Log.e("tag value", String.valueOf(tag));

        if (gamestatus[tag] == 2 && isGameRunning) {

            counter.setTranslationY(-1000f);
            gamestatus[tag] = playernumber;


            if (playernumber == 0) {
                counter.setImageResource(R.drawable.red);
                playernumber = 1;

            } else {
                counter.setImageResource(R.drawable.yellow);
                playernumber = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);

            for (int[] wincombination : wincombnations) {

                if (gamestatus[wincombination[0]] == gamestatus[wincombination[1]] &&
                        gamestatus[wincombination[1]] == gamestatus[wincombination[2]] &&
                        gamestatus[wincombination[0]] != 2) {

                    System.out.println("win!");
                    isGameRunning = false;

                    String winner = "Red";

                    if (gamestatus[wincombination[0]] == 1) {
                        winner = "yellow";
                    }

                    isWinnerAvail = true;
                    resultStatus.setVisibility(View.VISIBLE);
                    matchstatus.setText("Winner : " + winner);

                }

            }
            Boolean isDraw = true;

            for (int counterstate : gamestatus) {
                if (counterstate == 2) {
                    isDraw = false;
                }
            }

            if (isDraw && !isWinnerAvail) {

                resultStatus.setVisibility(View.VISIBLE);
                matchstatus.setText("Game Drawn");
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Tic-Tac-Toe");
    }

    public void playAgain(View view) {

        recreate();

    }
}
