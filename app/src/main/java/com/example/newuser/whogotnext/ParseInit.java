package com.example.newuser.whogotnext;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;import java.lang.Override;

/**
 * Created by newuser on 12/4/15.
 */
public class ParseInit extends Application {

    private final String  parse_app_id = "eyMzbtH56tAE2rMcm9TjBIvfSRI34uCDENAJ31ay";
    private final String  parse_client_id = "bosjczf81WGIAteEFZvfAhsIufxi3YgzpLWUYUlC";
    @Override

    public void onCreate(){
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, parse_app_id, parse_client_id);
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
