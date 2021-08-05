package com.example.parstagram2;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("36KSZU9G2UliuCaTTTEm0lheNsi9wwk9097bMMRs")
                .clientKey("QGP6fvAL3fEprdjqKHYXoFOdEJu0Nh68b7hlQBOt")
                .server("https://parseapi.back4app.com")
                .build());
    }
}
