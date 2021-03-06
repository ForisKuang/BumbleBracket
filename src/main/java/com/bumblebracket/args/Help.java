package com.bumblebracket.args;

import java.util.Map;
import java.util.TreeMap;


public class Help {

  public static final Map<String, String> HELP_OPTIONS = new TreeMap<String, String>(){
    {
      put("-upsert", "Insert or update particular entry");
      put("-top", "Lists top X entries specified");
      put("-drop", "Deletes the entry from the bracket");
      put("-get", "Gets the data for a single user");
    }
  };

  public static final Map<String, String> GET_OPTIONS = new TreeMap<String, String>() {
    {
      put("-all", "Display the entire bracket");
      put("[name]", "Display the information for that person");
      put("-top", "Display the top N people in the bracket where N is specified");
    }
  };

}
