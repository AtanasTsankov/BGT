package tsankov.atanas.boardgamestimer;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template_setup);
        Intent intent = getIntent();

        createSpinner(getPlayersItems(), R.id.playersAmount);
        createSpinner(getTurnsItems(), R.id.turnsPerRound);
        createSpinner(getRoundsItems(), R.id.roundsPerGame);
        createSpinner(getTimeBankItems(), R.id.timeBankAmount);
        timeBankStatus(false);
        Button btn = (Button)this.findViewById(R.id.setupButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setup();
            }
        });

    }

    public void setup(){
        Intent intent = new Intent(TemplateSetup.this, PlayersSetup.class);

        HashMap<String, Integer> dataMap = new HashMap<>();
        dataMap.put("P_Count", getSelectedSpinnerPosition(R.id.playersAmount));
        dataMap.put("T_Count", getSelectedSpinnerPosition(R.id.turnsPerRound));
        dataMap.put("R_Count", getSelectedSpinnerPosition(R.id.roundsPerGame));

        RadioGroup rGroup = (RadioGroup)findViewById(R.id.turnTimeGroup);
        int radioButtonID = rGroup.getCheckedRadioButtonId();
        View radioButton = rGroup.findViewById(radioButtonID);
        int radioIndex = rGroup.indexOfChild(radioButton);
        dataMap.put("T_Time", radioIndex);
        EditText gameName = (EditText)findViewById(R.id.gameName);
        String gameNameStr = gameName.getText().toString();
        intent.putExtra("Game_Name", gameNameStr);

        CheckBox timeBank = (CheckBox)findViewById(R.id.timeBank);
        if(timeBank.isChecked()){
            dataMap.put("TB_Amount", getSelectedSpinnerPosition(R.id.timeBankAmount));
        }

        intent.putExtra("DataMap", dataMap);

        TemplateSetup.this.startActivity(intent);
    }

    public int getSelectedSpinnerPosition(int id){
        Spinner spinner = (Spinner)findViewById(id);
        return spinner.getSelectedItemPosition();
    }

    protected void createSpinner(List items, int id) {
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
        Spinner sItems = (Spinner) findViewById(id);
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
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sameTurnTime:
                if (checked)
                    // Same duration for each turn during a round
                    break;
            case R.id.diffTurnTime:
                if (checked)
                    // Different duration for each turn during a round
                    break;
        }
    }

    public void onCheckboxClicked(View view){
        boolean checked = ((CheckBox) view).isChecked();
        timeBankStatus(checked);
    }

    public void timeBankStatus(boolean status){
        Spinner spinner = (Spinner)findViewById(R.id.timeBankAmount);
        spinner.setEnabled(status);
    }

    protected List<String> getPlayersItems(){
        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("How many players ...");
        spinnerItems.add("1 player");
        spinnerItems.add("2 players");
        spinnerItems.add("3 players");
        spinnerItems.add("4 players");
        spinnerItems.add("5 players");
        spinnerItems.add("6 players");
        spinnerItems.add("7 players");
        spinnerItems.add("8 players");
        return spinnerItems;
    }

    protected List<String> getTurnsItems(){

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("How many turns per round ...");
        spinnerItems.add("1 turn per round");
        spinnerItems.add("2 turns per round");
        spinnerItems.add("3 turns per round");
        spinnerItems.add("4 turns per round");
        spinnerItems.add("5 turns per round");
        spinnerItems.add("6 turns per round");
        spinnerItems.add("7 turns per round");
        spinnerItems.add("8 turns per round");
        spinnerItems.add("9 turns per round");
        spinnerItems.add("10 turns per round");
        return spinnerItems;
    }

    protected List<String> getRoundsItems(){

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("How many rounds during the game ...");
        spinnerItems.add("UNLIMITED / Until someone wins");
        spinnerItems.add("1(One) round total");
        spinnerItems.add("2(Two) rounds total");
        spinnerItems.add("3(Three) rounds total");
        spinnerItems.add("4(Four) rounds total");
        spinnerItems.add("5(Five) rounds total");
        spinnerItems.add("6(Six) rounds total");
        spinnerItems.add("7(Seven) rounds total");
        spinnerItems.add("8(Eight) rounds total");
        spinnerItems.add("9(Nine) rounds total");
        spinnerItems.add("10(Ten) rounds totals");
        return spinnerItems;
    }

    protected List<String> getTimeBankItems(){

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Time bank amount ...");
        spinnerItems.add("1(One) minute");
        spinnerItems.add("2(Two) minutes");
        spinnerItems.add("3(Three) minutes");
        spinnerItems.add("4(Four) minutes");
        spinnerItems.add("5(Five) minutes");
        spinnerItems.add("6(Six) minutes");
        spinnerItems.add("7(Seven) minutes");
        spinnerItems.add("8(Eight) minutes");
        spinnerItems.add("9(Nine) minutes");
        spinnerItems.add("10(Ten) minutes");
        return spinnerItems;
    }
}
