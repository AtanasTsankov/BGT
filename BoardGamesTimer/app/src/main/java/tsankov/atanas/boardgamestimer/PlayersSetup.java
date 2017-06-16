package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
        Integer players = (Integer) value.get("P_Count");

        RelativeLayout m_vwJokeLayout = (RelativeLayout) this.findViewById(R.id.relLayoutPlayers);



        for (int i = 2; i <= players; i++){
            RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            int idLastChild= m_vwJokeLayout.getChildAt(m_vwJokeLayout.getChildCount()-1).getId();
            lparams.addRule(RelativeLayout.BELOW, idLastChild);
            EditText tv= new EditText(this);
            tv.setId(idLastChild+1);
            tv.setLayoutParams(lparams);
            tv.setHint("Player " + i + " name...");
            m_vwJokeLayout.addView(tv);


        }

//        for (i = 1; i <= players; i++){
//            if(i == 1){
//                EditText txt = new EditText(this);
//                txt.setId(i.intValue());
//                txt.setHint("Player " + i + " name");
//                m_vwJokeLayout.addView(txt, lparams);
//            }else{
//                lparams.addRule(RelativeLayout.BELOW, i-1);
//                EditText txt = new EditText(this);
//                txt.setId(i.intValue());
//                txt.setHint("Player " + i + " name");
//                m_vwJokeLayout.addView(txt, lparams);
//            }
//
//        }

//        Button btn = new Button(this);
//        btn.setLayoutParams(lparams);
//        btn.setText("CONTNINUE");
//        btn.setPo

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
