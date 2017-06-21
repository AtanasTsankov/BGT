package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Locale;

public class EngageTimer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engage_timer);
        Intent intent = getIntent();
        HashMap turnDtls = (HashMap) intent.getSerializableExtra("TurnDetailsMap");
        final HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
        final Integer turns =  (Integer) value.get("T_Count");
        final HashMap names = (HashMap) intent.getSerializableExtra("NamesMap");
        final String gameName = (String) intent.getSerializableExtra("Game_Name");

        TextView gameNameView = (TextView) findViewById(R.id.GameNameTextField);
        gameNameView.setText(gameName);
        Integer currTurn = 1;
        Integer currPlayer = 1;
    }
}
