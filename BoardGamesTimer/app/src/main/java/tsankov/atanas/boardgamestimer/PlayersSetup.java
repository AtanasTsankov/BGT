package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class PlayersSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_setup);
        Intent intent = getIntent();
        HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
    }
}
