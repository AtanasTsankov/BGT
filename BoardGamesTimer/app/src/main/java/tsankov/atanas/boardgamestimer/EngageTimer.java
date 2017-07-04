package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class EngageTimer extends AppCompatActivity {
    private static HashMap value;
    private static Integer turns;
    private static HashMap names;
    private static HashMap turnDtls;
    private static String gameName;
    private static Integer playersCount;
    private static Integer roundsCount;
    private static HashMap timeBank;
    private static Integer currPlayerTB;
    private static Long currTimerMS;
    private static CountDownTimer timer;
    private static CountDownTimer tbTimer;
    private static TextView reserveTime;
    private static Boolean isInReserveTime;
    private static Long currTBTimerMS;

    private Integer currentPlayer;
    private Integer currentTurn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engage_timer);
        Intent intent = getIntent();
        this.turnDtls = (HashMap) intent.getSerializableExtra("TurnDetailsMap");
        this.value = (HashMap) intent.getSerializableExtra("DataMap");
        this.turns =  (Integer) value.get("T_Count");
        this.names = (HashMap) intent.getSerializableExtra("NamesMap");
        this.gameName = (String) intent.getSerializableExtra("Game_Name");
        this.playersCount = (Integer) value.get("P_Count");
        this.roundsCount = (Integer) value.get("R_Count");

        TextView gameNameView = (TextView) findViewById(R.id.GameNameTextField);
        gameNameView.setText(gameName);
        Integer timeBankDurationSeconds = ((Integer) value.get("TB_Amount"))*60;
        HashMap<String,Integer> personalTimeBank = new HashMap();
        for (Integer i = 1; i <= playersCount; i++){
            personalTimeBank.put("Player"+i.toString(), timeBankDurationSeconds);
        }
        this.timeBank = personalTimeBank;
        if(roundsCount != null && roundsCount > 0){
//            limitedRoundsGame();
        }else{
            unlimitedRoundsGame(1,1);
        }


    }

    public void unlimitedRoundsGame(Integer currentTurn, Integer currentPlayer){
        isInReserveTime = false;
        if(currentTurn > turns){
            currentTurn = 1;
        }
        if(currentPlayer > playersCount){
            currentPlayer = 1;
        }
        Boolean isJoint = false;
        Integer playerTimeBankRemaining = 0;
        this.currentPlayer = currentPlayer;
        this.currentTurn = currentTurn;
        ArrayList currentTurnDetails = (ArrayList) turnDtls.get(String.valueOf(currentTurn));
        String isJointTurn = (String) currentTurnDetails.get(2);
        reserveTime = (TextView) findViewById(R.id.ReserveTimeTextField);
        if(isJointTurn.equals("false")) {

            String playerName = (String) names.get("Player" + currentPlayer);
            TextView plName = (TextView) findViewById(R.id.PlayerNameTextField);
            plName.setText(playerName);

            playerTimeBankRemaining = (Integer) timeBank.get("Player"+currentPlayer);

            reserveTime.setText("RESERVE TIME: " + playerTimeBankRemaining + " SECONDS");
        }else{
            TextView plName = (TextView) findViewById(R.id.PlayerNameTextField);
            plName.setText("JOINT TURN");
            isJoint = true;
            reserveTime.setText("RESERVE TIME: " + playerTimeBankRemaining + " SECONDS");
        }
        reserveTime.setBackgroundColor(Color.LTGRAY);

        this.currPlayerTB = playerTimeBankRemaining;

        String turnName = (String) currentTurnDetails.get(0);
        TextView turnNameTextView = (TextView) findViewById(R.id.TurnNameTextField);
        turnNameTextView.setText(turnName);

        String turnDurationMinutes = (String) currentTurnDetails.get(1);
        Integer turnDurationSeconds = Integer.parseInt(turnDurationMinutes) * 60;
        final Integer turnDurationMiliSecs = turnDurationSeconds * 1000;
        final TextView timerTextView = (TextView) findViewById(R.id.turnTimerTextView);
        timerTextView.setText(turnDurationSeconds + " sec.");

        timer = new CountDownTimer(turnDurationMiliSecs, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + " sec.");
                currTimerMS = millisUntilFinished;
            }

            @Override
            public void onFinish() {
                if(currPlayerTB > 0){
                    startTBTimer();
                    timerTextView.setText("RESERVE TIME");
                    timerTextView.setBackgroundColor(Color.RED);
                }else{
                    timerTextView.setText("TIME IS UP !!!");
                    timerTextView.setBackgroundColor(Color.RED);
                    //// TODO: 04-Jul-17 play sound warning
                }
            }
        }.start();
        timerTextView.setBackgroundColor(Color.GREEN);

        Button b = (Button)findViewById(R.id.StartStopBtn);
        b.setText("PAUSE");
        b.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View v) {
                if (!isInReserveTime) {
                    Button b = (Button) v;
                    if (b.getText().equals("PAUSE")) {
                        timer.cancel();
                        b.setText("START");
                        timerTextView.setBackgroundColor(Color.YELLOW);
                    } else {
                        b.setText("PAUSE");
                        timerTextView.setBackgroundColor(Color.GREEN);
                        timer = new CountDownTimer(currTimerMS, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                timerTextView.setText(millisUntilFinished / 1000 + " sec.");
                                currTimerMS = millisUntilFinished;

                            }

                            @Override
                            public void onFinish() {
                                if (currPlayerTB > 0) {
                                    startTBTimer();
                                    timerTextView.setText("RESERVE TIME");
                                    timerTextView.setBackgroundColor(Color.RED);
                                } else {
                                    timerTextView.setText("TIME IS UP !!!");
                                    timerTextView.setBackgroundColor(Color.RED);
                                    //// TODO: 04-Jul-17 play sound warning
                                }

                            }
                        }.start();
                    }
                }else{
                    Button b = (Button) v;
                    if (b.getText().equals("PAUSE")) {
                        tbTimer.cancel();
                        b.setText("START");
                        reserveTime.setBackgroundColor(Color.YELLOW);
                    } else {
                        b.setText("PAUSE");
                        reserveTime.setBackgroundColor(Color.GREEN);
                        tbTimer = new CountDownTimer(currTBTimerMS, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                reserveTime.setText(millisUntilFinished / 1000 + " sec.");
                                currTBTimerMS = millisUntilFinished;

                            }

                            @Override
                            public void onFinish() {
                                currPlayerTB = 0;
                                reserveTime.setText("TIME IS UP !!!");
                                reserveTime.setBackgroundColor(Color.RED);
                                //// TODO: 04-Jul-17 play sound warning

                            }
                        }.start();
                    }
                }
            }

        });

    }

    private void startTBTimer(){
        isInReserveTime = true;
        reserveTime.setBackgroundColor(Color.GREEN);
        tbTimer = new CountDownTimer(currPlayerTB*1000 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                reserveTime.setText(millisUntilFinished/1000 + " sec.");
                currTBTimerMS = millisUntilFinished;
                currPlayerTB = (int) millisUntilFinished/1000;
            }

            @Override
            public void onFinish() {
                reserveTime.setText("TIME IS UP !!!");
                //// TODO: 04-Jul-17 play sound warning

            }
        }.start();
        reserveTime.setBackgroundColor(Color.GREEN);

    }


}
