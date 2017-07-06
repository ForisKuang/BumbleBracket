package com.bumblebracket.args;

import com.bumblebracket.Database;
import com.bumblebracket.cb.CBConfig;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import java.util.ArrayList;
import java.util.List;


public class Get {

  private CBConfig cb;

  public Get(CBConfig cb) {
    this.cb = cb;
  }

  public List<String> get(String name) {
    String query = "SELECT * FROM " + Database.TABLE + " WHERE name=" + "\""+ name + "\"";
    N1qlQueryResult result = cb.cbBucket.query(N1qlQuery.simple(query));
    return Database.query(query, cb);
  }

  public List<String> top(int num) {
    String query = "SELECT * FROM default WHERE " + num;
    return Database.query(query, cb);
  }

  public List<String> all() {
    String query = "SELECT * FROM " + Database.TABLE;
    return Database.query(query, cb);
  }

}
