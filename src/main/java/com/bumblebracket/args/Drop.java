package com.bumblebracket.args;

import com.bumblebracket.Database;
import com.bumblebracket.cb.CBConfig;
import com.couchbase.client.java.document.*;


public class Drop {

  private CBConfig cb;

  public Drop(CBConfig cb) {
    this.cb = cb;
  }

  public void drop(String name) {
      String query = "DELETE FROM " + Database.TABLE + "WHERE name=" + "\"" + name + "\"";
      Database.query(query, cb);
  }

}
