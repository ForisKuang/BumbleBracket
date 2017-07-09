package com.bumblebracket.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

  public String name;
  public String description;
  public int score;

  public User() {
    // Default constructor required for calls to DataSnapshot.getValue(User.class)
  }

  public User(String name, int score, String description) {
    this.name = name;
    this.score = score;
    this.description = description;
  }

}
