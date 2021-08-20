package com.example.parstagram2;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_AT = "createdAt";

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setImage(ParseFile imageFile) {
        put(KEY_IMAGE, imageFile);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }
}
