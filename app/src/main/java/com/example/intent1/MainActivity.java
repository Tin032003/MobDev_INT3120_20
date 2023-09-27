package com.example.intent1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView reply;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int code = result.getResultCode();

                    if (code == RESULT_OK) {
                        Intent data = result.getData();
                        String feedback = data.getStringExtra("feedback");
                        reply.setText(feedback);
                    } else {
                        reply.setText("???");
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)this.findViewById(R.id.myButton);
        editText = (EditText)this.findViewById(R.id.myText);
        reply = (TextView)this.findViewById(R.id.reply);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    public void sendMessage() {
        String input = this.editText.getText().toString();
        String message = "This is a message";

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("input", input);
        intent.putExtra("message", message);

        activityResultLauncher.launch(intent);
    }
}