package com.bumblebracket.args;

import com.bumblebracket.Database;
import com.bumblebracket.config.CBConfig;
import com.bumblebracket.config.Configs;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import java.util.List;


public class Get {

  private CBConfig cb;

  public Get(CBConfig cb) {
    this.cb = cb;
  }

  public List<String> get(String name) {
    String query = "SELECT * FROM " + Configs.TABLE + " WHERE name=" + "\""+ name + "\"";
    N1qlQueryResult result = cb.cbBucket.query(N1qlQuery.simple(query));
    return Database.query(query, cb);
  }

  public void get(String name, DatabaseReference database) {
    Query query = database.orderByChild("name").equalTo(name);
    query.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        for(String item : Database.removeExcess(dataSnapshot.getValue().toString())) {
          System.out.println(item);
        }
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

  public List<String> top(int num) {
    String query = "SELECT * FROM default WHERE " + num;
    return Database.query(query, cb);
  }

  public List<String> all() {
    String query = "SELECT * FROM " + Configs.TABLE;
    return Database.query(query, cb);
  }

}
