package com.bumblebracket.args;

import com.bumblebracket.cb.CBConfig;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import java.util.ArrayList;
import java.util.List;


public class Top {

  private CBConfig cb;

  public Top(CBConfig cb) {
    this.cb = cb;
  }

  public List<String> get(String name) {
    String query = "SELECT * FROM default WHERE name=" + name;
    N1qlQueryResult result = cb.cbBucket.query(N1qlQuery.parameterized(query, JsonArray.from("default")));
    System.out.println(result.toString());
    List<String> listResults = new ArrayList<String>();
    for(N1qlQueryRow row : result) {
      System.out.println(row);
      listResults.add(row.toString());
    }
    return listResults;
  }


}
