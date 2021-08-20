package com.example.parstagram2.Fragments;

import android.util.Log;

import com.example.parstagram2.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends HomeFragment{

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.include(Post.KEY_CREATED_AT);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.setLimit(20);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e == null) {
                    for (Post post : posts) {
                        Log.i(TAG, "Description: " + post.getDescription() + " and user: " + post.getUser().getUsername());
                    }
                    allPosts.addAll(posts);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "Error querying posts!", e);
                }
            }
        });
    }
}
