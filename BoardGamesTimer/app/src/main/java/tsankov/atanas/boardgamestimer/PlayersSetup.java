package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class PlayersSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_setup);
        Intent intent = getIntent();
        HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
        Integer players = (Integer) value.get("P_Count");

        LinearLayout m_vwJokeLayout = (LinearLayout) this.findViewById(R.id.lnrLayoutPlayers);

        for (int i = 1; i <= players; i++){


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        EditText tv= new EditText(this);
        tv.setLayoutParams(lparams);
        tv.setHint("Player " + i + " name");
        m_vwJokeLayout.addView(tv);

        }

//        final EditText[] playersNames = new EditText[players];
//
//        LinearLayout myLayout = (LinearLayout)findViewById(R.id.linearLayoutPlayers);
//
//        for(int i = 1; i <= players; i++){
//            final EditText plName = new EditText(this);
//            plName.setHint("Player " + i + " name");
//            myLayout.addView(plName);
//            playersNames[i] = plName;
//        }
    }
}
