package com.bumblebracket;

import com.bumblebracket.args.Help;
import com.bumblebracket.args.Top;
import com.bumblebracket.args.Upsert;
import com.bumblebracket.cb.CBConfig;
import com.bumblebracket.Database;
import com.google.firebase.database.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CBConfig cbConfig = new CBConfig();
        // Initialize Firebase instance
        try {
        // [START initialize]
        FileInputStream serviceAccount = new FileInputStream("service-account.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredential(FirebaseCredentials.fromCertificate(serviceAccount))
            .setDatabaseUrl(Database.DATABASE_URL)
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
      DatabaseReference database = FirebaseDatabase.getInstance().getReference();

      // Start listening to the Database
      startListeners();
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
                            return;
                        }
                        System.out.print("Amount to be subtracted: ");
                        int score = scan.nextInt();
                        upsert.upsert(name, score, description);
                    }
            } else if (args[0].equals("-top")) {
                    System.out.println("Error: Feature not supported yet");
            } else if (args[0].equals("-drop")) {
                    System.out.println("Error: Feature not supported yet");
            } else if(args[0].equals("-get")) {
                    if(args.length == 1) {
                      System.out.println("Error: Requires name");
                      return;
                    }
                    Top top = new Top(cbConfig);
                    List<String> result = top.get(args[1]);
                    System.out.println("Got result");
                    for(String s : result) {
                      System.out.print(s + " ");
                    }
            } else {
                System.out.println("Error: Not a command");
            }

        }
    }
}
