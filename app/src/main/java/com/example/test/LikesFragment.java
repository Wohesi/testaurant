package com.example.test;


import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;


public class LikesFragment extends Fragment {

    // get firebase information
    private DatabaseReference mDatabaseRef;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String userID;

    // Store the movies into a listview
    private ListView mListView;
    private ArrayList<String> likesArray = new ArrayList<>();
    private ArrayList<Object> objectArrayList;


    public LikesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // set firebase refeences
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseRef = mFirebaseDatabase.getReference();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        userID = firebaseUser.getUid();

        setAuthStateListener();

        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.likes_fragment, container, false);

       // set the list view to load liked items
       mListView = (ListView) rootView.findViewById(R.id.listView);

       mDatabaseRef.child("users").addListenerForSingleValueEvent(
               new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {

                        // Single item
                       String singleMovieKey = dataSnapshot.child("users").child(userID).child("movies").getKey();
                       String singleMovie = dataSnapshot.child("users").child(userID).child("movies").getValue().toString();

                       Log.d("movie", dataSnapshot.child("users").child(userID).child("movies").child(singleMovieKey).getValue().toString());
                       likesArray.add(singleMovie);

                       // multiple items?
                       for (DataSnapshot snapshot: dataSnapshot.getChildren()) {

                           Object movies =dataSnapshot
                                   .child(userID)
                                   .child("movies").getKey();
                                   //.getValue();

                           Log.d("item", snapshot.child("movies").getValue().toString());

                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               }
       );

       ArrayAdapter likesAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,  likesArray);
        mListView.setAdapter(likesAdapter);

       return rootView;
    }

    // How to show the data into a listview
    // Used the following tutorial:
    // https://www.youtube.com/watch?v=2duc77R4Hqw



    private void setAuthStateListener() {

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null) {
                    Log.d("logged in", "logged in user: " + userID);
                } else {
                    gotoLoginActivity();
                }

            }
        };
    }

    private void gotoLoginActivity() {
        Intent LoginIntent = new Intent(LikesFragment.this.getContext(), LoginFragment.class);
        startActivity(LoginIntent);
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

}