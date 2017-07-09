package com.bumblebracket.config;

import com.couchbase.client.java.*;


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
