package com.bumblebracket.args;

import com.bumblebracket.Database;
import com.bumblebracket.config.CBConfig;
import com.bumblebracket.config.Configs;
import com.bumblebracket.model.User;
import com.couchbase.client.java.document.*;
import com.couchbase.client.java.document.json.JsonObject;
import com.google.firebase.database.DatabaseReference;
import java.util.List;


public class Upsert {

  private CBConfig cb;

  public Upsert(CBConfig cb) {
    this.cb = cb;
  }

  public void upsert(String name, String description) {
    JsonObject obj = JsonObject.create()
        .put("name", name)
        .put("description", description)
        .put("score", 100);
    cb.cbBucket.upsert(JsonDocument.create("u:" + name, obj));
  }

  public void upsert(String name, int score, String description) {
    JsonObject obj = JsonObject.create()
        .put("name", name)
        .put("description", description)
        .put("score", score);
    cb.cbBucket.upsert(JsonDocument.create("u:"+name, obj));
  }

  public void upsert(String name, String description, DatabaseReference database) {
    database.child(name).setValue(new User(name, 100, description));

  }

    public void upsert(String name, int score, String description, DatabaseReference database) {
    database.child(name).setValue(new User(name, score, description));
  }

  public void updateScore(String name, int score) {
    // Get the current score to see how much to subtract
    Get get = new Get(cb);
    List<String> results = get.get(name);
    int currScore = Integer.parseInt(results.get(results.size()-1));
    currScore -= score;
    // If the score is less than zero, they are removed from the bracket
    if (currScore <= 0 ) {
      Drop drop = new Drop(cb);
      drop.drop(name);
      return;
    }
    String query = "UPDATE " + Configs.TABLE + " SET score=" + currScore + " WHERE name=" + "\"" + name + "\"";
  }

}
