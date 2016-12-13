package com.meajireview.meajireview_android;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by songm on 2016-11-19.
 */

public class Meajireview extends Application {
    String APPLICATION_ID = "fn7Cm5P4P9yPvZ1LGZYnQfNmkrTsODaePgeRQYXR";
    String CLIENT_KEY = "hnMSndoGjcbowJ2iO24aFOiwLL2UAulGg5tUkFA0";
    @Override
    public void onCreate() {
            super.onCreate();
            Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
            ParseACL defaultACL = new ParseACL();
            defaultACL.setPublicReadAccess(true);
            defaultACL.setPublicWriteAccess(true);
            ParseACL.setDefaultACL(defaultACL, true);
    }
}
