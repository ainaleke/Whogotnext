package com.example.newuser.whogotnext;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LocationListener, FacebookFragment.OnFragmentInteractionListener{



    // UI references.
    private EditText userName;
    private EditText passwordText;
    private Button logInButton;
    private TextView signUpText;
    private LoginButton faceBookButton;
    String name;
    String email;
    ParseUser parseUser;
    Location location;
    LocationManager userLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the login form.


        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
//        faceBookButton = (LoginButton) findViewById(R.id.facebook_login_button);


        // Set up the login form.
        userName = (EditText) findViewById(R.id.userName);
//        populateAutoComplete();
        passwordText = (EditText) findViewById(R.id.input_password);
        logInButton = (Button)findViewById(R.id.button_login);
        signUpText = (TextView)findViewById(R.id.link_signup);


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    logIn();
            }
        });


        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

    }


    public void signUp(){
        Intent signUpIntent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(signUpIntent);
    }


    public void logIn(){
        parseUser.logInInBackground(userName.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {

                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        userLocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        location = userLocation.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if(location!=null){
                            ParseGeoPoint geoPoint = new ParseGeoPoint(location.getLatitude(),location.getLongitude());
                            user.put("current_location",geoPoint);
                            user.saveInBackground();
                        }

                    }
                    Intent toChooseActivity = new Intent(getApplicationContext(), ChooseActivity.class);
                    startActivity(toChooseActivity);
                    Toast.makeText(getApplicationContext(), "You have successfully logged in.", Toast.LENGTH_LONG).show();

                    passwordText.setText("");
                    userName.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Checked username or password.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void setUserLocation(Location location){}

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}