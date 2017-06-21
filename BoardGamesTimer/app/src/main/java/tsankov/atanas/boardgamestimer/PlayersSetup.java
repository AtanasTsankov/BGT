package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

public class PlayersSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_setup);
        Intent intent = getIntent();
        final HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
        final Integer players = (Integer) value.get("P_Count");
        final String gameName = (String) intent.getSerializableExtra("Game_Name");

        RelativeLayout m_vwJokeLayout = (RelativeLayout) this.findViewById(R.id.relLayoutPlayers);


        Integer i;
        for (i = 1; i <= players; i++){
            if(i == 1){
                RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                EditText tv= new EditText(this);
                tv.setId(i.intValue());
                tv.setLayoutParams(lparams);
                tv.setHint("Player " + i + " name...");
                m_vwJokeLayout.addView(tv);
            }else {
                RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                int idLastChild = m_vwJokeLayout.getChildAt(m_vwJokeLayout.getChildCount() - 1).getId();
                lparams.addRule(RelativeLayout.BELOW, idLastChild);
                EditText tv = new EditText(this);
                tv.setId(idLastChild + 1);
                tv.setLayoutParams(lparams);
                tv.setHint("Player " + i + " name...");
                m_vwJokeLayout.addView(tv);
            }


        }

        Button btn = (Button)findViewById(R.id.contBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueSetup(players, value, gameName);
            }
        });


    }

    public void continueSetup(Integer players, HashMap dataMap, String gameName){
        Intent intent = new Intent(PlayersSetup.this, TurnsSetup.class);
        HashMap names = new HashMap();
        Integer i;
        for (i = 1; i<= players; i++){
            EditText name = (EditText)findViewById(i.intValue());
            names.put("Player"+i, name.getText().toString());
        }
        intent.putExtra("NamesMap", names);
        intent.putExtra("DataMap", dataMap);
        intent.putExtra("Game_Name", gameName);

        PlayersSetup.this.startActivity(intent);

    }

}
