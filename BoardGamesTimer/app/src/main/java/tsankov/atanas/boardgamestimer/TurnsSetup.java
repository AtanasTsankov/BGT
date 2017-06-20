package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class TurnsSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turns_setup);

        Intent intent = getIntent();
        final HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
        final Integer turns =  (Integer) value.get("T_Count");
        LinearLayout relLayout = (LinearLayout)findViewById(R.id.linLayout);

        String[] spinnerItems = new String[]{"1 minute", "2 minutes", "3 minutes", "4 minutes", "5 minutes", "6 minutes",
                "7 minutes", "8 minutes", "9 minutes", "10 minutes"};

        Integer i;
        for(i = 1; i <= turns; i++){
            if(i == 1){
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                TextView tv= new TextView(this);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                tv.setId(i.intValue());
                lparams.setMargins(30,20,30,0);
                tv.setLayoutParams(lparams);
                tv.setText("Turn " + i);
                relLayout.addView(tv);

                EditText turnName = new EditText(this);
                turnName.setId(i + 1);
//                lparams.(RelativeLayout.BELOW, i);
                turnName.setLayoutParams(lparams);
                turnName.setHint("Turn name");
                relLayout.addView(turnName);

                Spinner turnDuration = new Spinner(this);
                turnDuration.setId(i + 2);
                LinearLayout.LayoutParams lparams2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lparams2.setMargins(30,20,30,0);
                turnDuration.setLayoutParams(lparams2);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, spinnerItems);
                turnDuration.setAdapter(adapter);
                relLayout.addView(turnDuration);
            }else{
                LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                int idLastChild = relLayout.getChildAt(relLayout.getChildCount() - 1).getId();
                TextView tv= new TextView(this);
                tv.setId(idLastChild + 1);
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                lparams.setMargins(30,20,30,0);
                tv.setLayoutParams(lparams);
                tv.setText("Turn " + i);
                relLayout.addView(tv);

                EditText turnName = new EditText(this);
                turnName.setId(idLastChild + 2);
                turnName.setLayoutParams(lparams);
                turnName.setHint("Turn name");
                relLayout.addView(turnName);

                Spinner turnDuration = new Spinner(this);
                turnDuration.setId(idLastChild + 3);
                LinearLayout.LayoutParams lparams2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lparams2.setMargins(30,20,30,0);
                turnDuration.setLayoutParams(lparams2);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, spinnerItems);
                turnDuration.setAdapter(adapter);
                relLayout.addView(turnDuration);
            }
        }
    }
}
