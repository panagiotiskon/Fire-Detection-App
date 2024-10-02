package com.example.firedetectionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner roomSpinner;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomSpinner = findViewById(R.id.room_spinner);
        nextButton = findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRoom = roomSpinner.getSelectedItemPosition();
                Intent intent = new Intent(MainActivity.this, FloorPlanActivity.class);
                intent.putExtra("selectedRoom", selectedRoom);
                startActivity(intent);
            }
        });
    }
}
