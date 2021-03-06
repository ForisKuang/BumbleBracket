package com.bumblebracket;

import com.bumblebracket.config.CBConfig;
import com.bumblebracket.model.User;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;


public class Database {

  public static DatabaseReference database;

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

  public static void addNewUserChangedListener(User user, String postId) {
    // Get references to the post
    final DatabaseReference postRef = database.child("posts").child(postId);
  }

  public static void startListeners(DatabaseReference database) {
    database.child("posts").addChildEventListener(new ChildEventListener()  {

      public void onChildAdded(DataSnapshot dataSnapshot, String prevChildName) {
        final String postId = dataSnapshot.getKey();
        final User user = dataSnapshot.getValue(User.class);

        // Listen for changes in the number of users and update usersCount
        addNewUserChangedListener(user, postId);

        // Listen for new users on the post, notify users on changes
        addNewUsersListener(dataSnapshot.getRef(), user);
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

  public static List<String> query(String query, CBConfig cb) {
    N1qlQueryResult result = cb.cbBucket.query(N1qlQuery.simple(query));
    return removeExcess(result);
  }

  public static List<String> removeExcess(String result) {
    List<String> listResult = new ArrayList<String>();
    String[] resultArr = result.split("\\{+|\\:+|\\}+");
    for(int i = 0; i < resultArr.length; i++) {
      listResult.add(resultArr[i]);
    }
    return listResult;
  }

  private static List<String> removeExcess(N1qlQueryResult result) {
    List<String> listResults = new ArrayList<String>();
    for(N1qlQueryRow row : result) {
      String[] rowStrArr = row.toString().replaceAll("\\s+","").split("\\\"+|\\{+|\\,+|\\:+|\\}+|default");
      for(int i = 0; i < rowStrArr.length; i++) {
        if (!rowStrArr[i].equals("")) {
          listResults.add(rowStrArr[i]);
        }
      }
    }
    return listResults;
  }


}
