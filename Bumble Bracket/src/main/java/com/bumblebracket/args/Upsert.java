package com.bumblebracket.args;

import com.bumblebracket.cb.CBConfig;
import com.couchbase.client.java.document.*;
import com.couchbase.client.java.document.json.JsonObject;


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

}
