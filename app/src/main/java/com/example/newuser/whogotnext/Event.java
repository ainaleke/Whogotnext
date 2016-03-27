package com.example.newuser.whogotnext;

import com.parse.ParseObject;

/**
 * Created by kylewoumn on 3/26/16.
 */
public class Event extends ParseObject {

    public Event (){
        super();
    }
    public String getEventTitle(){
        return getString("event_title");
    }
    public String getObjectId(){
        return getString("objectId");
    }
}
