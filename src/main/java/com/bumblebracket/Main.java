package com.bumblebracket;

import com.bumblebracket.args.Help;
import com.bumblebracket.args.Get;
import com.bumblebracket.args.Upsert;
import com.bumblebracket.config.CBConfig;
import com.bumblebracket.config.Configs;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    CBConfig cbConfig = new CBConfig();

    // Initialize Firebase instance
    try {
      // [START initialize]
      FileInputStream serviceAccount = new FileInputStream(Configs.PATH_TO_JSON);
      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
          .setDatabaseUrl(Configs.DATABASE_URL)
          .build();
      FirebaseApp.initializeApp(options);
      // [END initialize]
    } catch (FileNotFoundException e) {
      System.out.println("ERROR: invalid service account credentials. See README.");
      System.out.println(e.getMessage());

      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Shared Database reference
    Database.database = FirebaseDatabase.getInstance().getReference();

    // Start listening to the Database
    Database.startListeners(Database.database);

    if(args.length != 0) {
      if(args[0].equals("-help")) {
        for (String key : Help.HELP_OPTIONS.keySet()) {
          System.out.println(key + "      " + Help.HELP_OPTIONS.get(key));
        }
      } else if (args[0].equals("-upsert")) {
        if(args.length == 1) {
          System.out.println("Error: Requires name");
          return;
        } else {
          Upsert upsert = new Upsert(cbConfig);
          String name = args[1];
          System.out.print("Input description: " );
          String description = scan.nextLine();
          System.out.print("Input score (y/n): ");
          String response = scan.next();
          if(!response.toLowerCase().startsWith("y")) {
            upsert.upsert(name, description);
            upsert.upsert(name, description, Database.database.child("users"));
            return;
          }
          System.out.print("Amount to be subtracted from initial 100: ");
          int score = scan.nextInt();
          upsert.upsert(name, score, description);
          upsert.upsert(name, score, description, Database.database.child("users"));
        }
      } else if (args[0].equals("-drop")) {
        System.err.println("Error: Feature not supported yet");
      } else if(args[0].equals("-get")) {
        Get get = new Get(cbConfig);
        List<String> result = new ArrayList<String>();
        if(args.length == 1) {
          System.err.println("Error: Requires another parameter, type -h for help");
          return;
        } else if (args[1].equals("-h")) {
          for(String key : Help.GET_OPTIONS.keySet()) {
            System.out.println(key + "  " + Help.GET_OPTIONS.get(key));
          }
        } else if (args[1].equals("-all")) {
          result = get.all();
        } else if (args[1].equals("-top")) {
          if (args.length == 2) {
            System.err.println("Error: Requires another parameter, the number of people at the top of the database to be displayed");
            return;
          }
          result = get.top(Integer.parseInt(args[2]));
        } else {
          result = get.get(args[1]);
        }
        System.err.println(result.size());
        for(String s : result) {
          System.out.println(s);
        }
      } else {
        System.err.println("Error: Not a command");
      }

    }
  }
}
