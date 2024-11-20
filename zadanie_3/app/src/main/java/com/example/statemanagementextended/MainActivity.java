package com.example.statemanagementextended;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";
    private static final String KEY_EDITTEXT = "editText";
    private static final String KEY_CHECKBOX = "checkBox";
    private static final String KEY_SWITCH = "switch1";
    //klucz do przechowywania danych w Bundle

    private TextView textViewCount;
    private int count = 0;
    private EditText editTextText;
    private CheckBox checkBox;
    private Switch switch1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCount = findViewById(R.id.textView);
        Button buttonIncrement = findViewById(R.id.button);
        editTextText = findViewById(R.id.editTextText);
        checkBox = findViewById(R.id.checkBox);
        switch1 = findViewById(R.id.switch1);
        textView2 = findViewById(R.id.textView2);

        //odczyt danych ze stanu, jezeli istnieje
        if(savedInstanceState != null){
            count = savedInstanceState.getInt(KEY_COUNT);
            editTextText.setText(savedInstanceState.getString(KEY_EDITTEXT));
            checkBox.setChecked(savedInstanceState.getBoolean(KEY_CHECKBOX));
            switch1.setChecked(savedInstanceState.getBoolean(KEY_SWITCH));
            //przwroc wartosc licznika w przypadku znalezenia
        }
        updateCountText();
        updateCheckBox();

        buttonIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCountText();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCheckBox();
            }
        });



    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_EDITTEXT, String.valueOf(editTextText));
        outState.putBoolean(KEY_CHECKBOX, checkBox.isChecked());
        outState.putBoolean(KEY_SWITCH, switch1.isChecked());

        // zapisz aktualny stan licznika do Bundle
    }

    private void updateCountText(){
        textViewCount.setText("Licznik: " + count);
    }

    private void updateCheckBox() {
        if (checkBox.isChecked()) {
            textView2.setText("Opcja zaznaczona");
        } else {
            textView2.setText("");
        }
    }
}