package com.example.newuser.whogotnext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

public class CreateSessionActivty extends AppCompatActivity {

    private Button button;
    private EditText text;
    private EditText size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session_activty);

        button = (Button) findViewById(R.id.button_create);
        text = (EditText)findViewById(R.id.name);
        size = (EditText) findViewById(R.id.size);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject object = new ParseObject("Session");
                object.put("Name",text.getText().toString());
                object.put("Size", Integer.parseInt(size.getText().toString()));
                object.saveInBackground();
                Intent toListOfSession = new Intent(getApplicationContext(),ListOfSessionActivity.class);
                startActivity(toListOfSession);
            }
        });
    }
}
