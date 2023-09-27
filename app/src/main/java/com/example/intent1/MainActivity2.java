package com.example.intent1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView;
    String input;
    String message;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        button = (Button)findViewById(R.id.backButton);
        input = intent.getStringExtra("input");
        message = intent.getStringExtra("message");
        textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                String feedback = "Feedback for input: " + input;
                data.putExtra("feedback", feedback);

                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }





}