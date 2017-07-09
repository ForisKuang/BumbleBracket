package com.bumblebracket.args;

import com.bumblebracket.Database;
import com.bumblebracket.config.CBConfig;
import com.bumblebracket.config.Configs;


public class Drop {

  private CBConfig cb;

  public Drop(CBConfig cb) {
    this.cb = cb;
  }

  public void drop(String name) {
      String query = "DELETE FROM " + Configs.TABLE + "WHERE name=" + "\"" + name + "\"";
      Database.query(query, cb);
  }

}
