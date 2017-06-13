package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class TemplateSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_setup);
        Intent intent = getIntent();
        Integer value = intent.getIntExtra("test" , 0);
        EditText myText = (EditText) findViewById(R.id.editText2);
        myText.setText(value.toString());

    }
}
