package com.example.test;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wout on 14-12-2017.
 */

public class UserLikes implements Serializable {

    public String movie;

    public UserLikes() {
    }

    public  UserLikes(String movie) {
        this.movie = movie;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("movie", movie);

        return result;
    }
}
