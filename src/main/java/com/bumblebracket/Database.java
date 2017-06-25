package com.bumblebracket;

import com.bumblebracket.posts.User

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Database {

  public static final String DATABASE_URL = "https://bumble-bracket.firebaseio.com/";

  private static void addNewUsersListener(final DatabaseReference postRef, final User user) {
    postRef.child("users").addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {

      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
  }

  public static void addNewUserChangedListener(User user)

  public static void startListeners(DatabaseReference database) {
    database.child("posts").addChildEventListener(new ChildEventListener() {

      public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {
        final String postId = dataSnapshot.getKey();
        final User user = dataSnapshot.getValue(User.class);

        // Listen for changes in the number of stars and update starCount
        addNewUserChangedListener(user, postId);

        // Listen for new stars on the post, notify users on changes
        addNewUsersListener(dataSnapshot.getRef(), user;
      }

      public void onChildChanged(DataSnapshot dataSnapshot, String prevChildName) {}

      public void onChildRemoved(DataSnapshot dataSnapshot) {}

      public void onChildMoved(DataSnapshot dataSnapshot, String prevChildName) {}

      public void onCancelled(DatabaseError databaseError) {
        System.out.println("startListeners: unable to attach listener to posts");
        System.out.println("startListeners: " + databaseError.getMessage());
      }
    });
  }



}
