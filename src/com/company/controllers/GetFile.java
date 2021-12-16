package com.company.controllers;

import java.io.*;
import java.util.ArrayList;

public class GetFile {

    public static String[] categories;
    public static boolean exists = false;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static PrintWriter pw = null;

    public static void GetFile() {

        try {
            fw = new FileWriter("categories.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            ReadFile();
            exists = true;
        } catch (IOException e) { e.printStackTrace();}

    }

    public static void AddToFile(String catName) {

        try {

            pw.println(catName);
            //System.out.println("Data Successfully appended into file");
            pw.flush();
        }

        finally { try {
            pw.close();
            bw.close();
            fw.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        }
    }

    public static void ReadFile() {

        ArrayList<String> list = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader("categories.txt"))) {
            while (br.ready()) {
                list.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        categories = list.toArray(new String[list.size() + 1]);
        //System.out.println("\nreading file...");

    }
}
