package com.example.newuser.whogotnext;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class ListOfSessionActivity extends AppCompatActivity {

    private FloatingActionButton fabButton;
    List<Event> mList=new ArrayList<Event>();
    SportsListAdapter arrayAdapter=null;
    ListView sportsListView;
    String objectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_events);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabButton = (FloatingActionButton) findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creteSession = new Intent(getApplicationContext(),CreateSessionActivty.class);
                startActivity(creteSession);
            }
        });
    }


    private void queryEvents() {
        ParseQuery<Event> query = ParseQuery.getQuery("Session");
        query.findInBackground(new FindCallback<Event>() {
            @Override
            public void done(List<Event> objectList, ParseException e) {
                if (e == null) {
                    mList = objectList;
                    arrayAdapter = new SportsListAdapter(getApplicationContext(), objectId, mList);
                    sportsListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();

                } else {
                    Log.e("Error", e.toString());
                    Toast.makeText(getApplicationContext(), "Error Loading Events", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
