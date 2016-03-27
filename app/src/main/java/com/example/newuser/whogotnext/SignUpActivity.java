package com.example.newuser.whogotnext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private TextView logInText;
    private EditText username;
    private EditText password;
    private EditText passwordConfirmation;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        createAccountButton = (Button) findViewById(R.id.button_signup);
        logInText = (TextView) findViewById(R.id.link_login);




        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser newUser = new ParseUser();
                newUser.setUsername(username.getText().toString());
                newUser.setPassword(password.getText().toString());
                newUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(),username+ " you have successfully created an account",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.e("PARSE SIGNUP ERROR", e.toString());
                            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
                Intent toLogin = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(toLogin);
            }
        });
        logInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent toLogin = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(toLogin);
            }
        });
    }

    public void setPasswordConfirmation(){
        if(!password.getText().toString().equals(passwordConfirmation.getText().toString())){
                Toast.makeText(getApplicationContext(),"Your passwords do not match",Toast.LENGTH_LONG ).show();
        }
    }
}

