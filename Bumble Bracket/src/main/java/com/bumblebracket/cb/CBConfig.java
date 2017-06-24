package com.bumblebracket.cb;

import com.couchbase.client.java.*;
import com.couchbase.client.java.document.*;
import com.couchbase.client.java.document.json.*;
import com.couchbase.client.java.query.*;

public class CBConfig {

  private String url;
  private String bucket;
  public Bucket cbBucket;
  private Cluster cluster;

  public CBConfig() {
    url = "localhost";
    bucket = "default";
    cluster = CouchbaseCluster.create(url);
    cbBucket = cluster.openBucket(bucket);
  }

}
