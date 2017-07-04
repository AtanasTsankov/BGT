package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TurnsSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turns_setup);

        Intent intent = getIntent();
        final HashMap value = (HashMap) intent.getSerializableExtra("DataMap");
        final Integer turns =  (Integer) value.get("T_Count");
        final HashMap names = (HashMap) intent.getSerializableExtra("NamesMap");
        final String gameName = (String) intent.getSerializableExtra("Game_Name");
        LinearLayout relLayout = (LinearLayout)findViewById(R.id.linLayout);

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Turn duration ...");
        spinnerItems.add("1 minute");
        spinnerItems.add("2 minutes");
        spinnerItems.add("3 minutes");
        spinnerItems.add("4 minutes");
        spinnerItems.add("5 minutes");
        spinnerItems.add("6 minutes");
        spinnerItems.add("7 minutes");
        spinnerItems.add("8 minutes");
        spinnerItems.add("9 minutes");
        spinnerItems.add("10 minutes");

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

                Spinner turnDuration = createSpinner(spinnerItems, i+2);
                turnDuration.setLayoutParams(lparams);
                relLayout.addView(turnDuration);

                CheckBox personal = new CheckBox(this);
                personal.setText("Joint turn");
                personal.setId(i+3);
                personal.setChecked(false);
                personal.setLayoutParams(lparams);
                relLayout.addView(personal);
                if(turns == 1){
                    Button btn = new Button(this);
                    btn.setText("START");
                    btn.setLayoutParams(lparams);
                    btn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            engageTimers(turns, value, names, gameName);
                        }
                    });
                    relLayout.addView(btn);
                }
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

                Spinner turnDuration = createSpinner(spinnerItems, idLastChild+3);
                turnDuration.setLayoutParams(lparams);
                relLayout.addView(turnDuration);

                CheckBox personal = new CheckBox(this);
                personal.setText("Joint turn");
                personal.setId(idLastChild+4);
                personal.setChecked(false);
                personal.setLayoutParams(lparams);
                relLayout.addView(personal);

                if(i == turns){
                    Button btn = new Button(this);
                    btn.setText("START");
                    btn.setLayoutParams(lparams);
                    btn.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){
                            engageTimers(turns, value, names, gameName);
                        }
                    });
                    relLayout.addView(btn);
                }
            }
        }
    }

    protected Spinner createSpinner(List items, int id) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, items) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent){
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0){
                    tv.setTextColor(Color.GRAY);
                }
                else{
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = new Spinner(this);
        sItems.setId(id);
        sItems.setAdapter(adapter);
        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if(position > 0){
                    Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });
        return sItems;
    }

    public void engageTimers(int turns, HashMap value, HashMap names, String gameName){
        HashMap<String,ArrayList<String>> turnsDetails = new HashMap<String,ArrayList<String>>();
        Integer i;
        Integer turnNumber = 1;
        for(i = 1; i < turns*4;){
            i++;
            if(i>2){
                i++;
            }
            ArrayList<String> tDtls = new ArrayList<String>();
            EditText tName = (EditText)findViewById(i.intValue());
            i++;

            tDtls.add(tName.getText().toString());
            Spinner tDur = (Spinner)findViewById(i.intValue());
            i++;
            Integer poss = tDur.getSelectedItemPosition();
            tDtls.add(poss.toString());
            CheckBox isJoint = (CheckBox)findViewById(i.intValue());
            if(isJoint.isChecked()){
                tDtls.add("true");
            }else{
                tDtls.add("false");
            }
            turnsDetails.put(Integer.toString(turnNumber), tDtls);
            turnNumber++;
        }
        Intent intent = new Intent(TurnsSetup.this, EngageTimer.class);
        intent.putExtra("TurnDetailsMap", turnsDetails);
        intent.putExtra("T_Count", turns);
        intent.putExtra("DataMap",value);
        intent.putExtra("NamesMap", names);
        intent.putExtra("Game_Name", gameName);
        TurnsSetup.this.startActivity(intent);
    }
}
