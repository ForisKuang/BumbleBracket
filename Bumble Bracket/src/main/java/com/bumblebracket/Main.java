package com.bumblebracket;

import com.bumblebracket.args.Help;
import com.bumblebracket.args.Top;
import com.bumblebracket.args.Upsert;
import com.bumblebracket.cb.CBConfig;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CBConfig cbConfig = new CBConfig();
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
