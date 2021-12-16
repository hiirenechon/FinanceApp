package com.company.views;

import com.company.controllers.DBConnect;
import com.company.models.Category;

import java.util.ArrayList;
import java.util.Scanner;

public class CmdLineView {

    public static float amount;
    public static String comment;
    public static int budget; //true is earned and false is spent
    public static float totalEarned;
    public static float totalSpent;
    public static boolean noEntries;
    private static int year;
    private static int month;
    private static int day;

    public void CmdLineView(String day) {
        //Greeting
        System.out.println("Personal Finance Tracker");
        System.out.println("========================\n");
        System.out.println("Today's date is " + day);
    }

    public static int MainMenu() {
        System.out.print("\nMain Menu:\n");
        System.out.print("1. Add Entry\n");
        System.out.print("2. View Finance\n");
        System.out.print("3. End Program\n");
        System.out.print("Your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        int i = 0;
        while(i == 0) {
            if (choice != 1 && choice != 2 && choice != 3) {
                System.out.print("INVALID INPUT - choose 1, 2, or 3: \n");
            }
            else {
                i = 1;
            }
        }
        return choice;
    }

    public static int ViewMenu() {
        System.out.print("View Menu:\n");
        System.out.print("1. Today's Finance\n");
        System.out.print("2. Different Day's Finance\n");
        System.out.print("3. Month's Finance\n");
        System.out.print("4. Year's Finance\n");
        System.out.print("Your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        int i = 0;
        while(i == 0) {
            if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
                System.out.print("INVALID INPUT - choose 1, 2, 3, or 4: \n");
            }
            else {
                i = 1;
            }
        }
        return choice;
    }

    public static int ChooseCat(int num, String[] cats) {
        System.out.print("Choose a category:\n");
        for(int i = 0; i < num; i++)
        {
            System.out.print( i + 1 + ". " + cats[i] + "\n");
        }
        System.out.print( num + 1 + ". Create new Spending Category\n");
        System.out.print("Your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        int m = 0;
        while(m == 0) {
            if (choice > (num+1)) {
                System.out.print("INVALID INPUT - enter again: \n");
            }
            else {
                m = 1;
            }
        }
        return choice;
    }

    public static String MakeNewCat(int num, String[] cats) {
        System.out.print("Enter name for new category: ");
        Scanner input = new Scanner(System.in);
        String catName = input.nextLine();
        cats[num - 1] = catName;
        return catName;
    }

    public static void GetInfo() {
        System.out.println("Enter a comment: ");
        Scanner input = new Scanner(System.in);
        comment = input.nextLine();

        System.out.println("Enter the amount: ");
        int m = 0;
        while(m == 0) {
            if (input.hasNextDouble()) {
                amount = input.nextFloat();
                m = 1;
            } else {
                System.out.println("INVALID INPUT - Enter a valid value: ");
            }
        }

        System.out.println("Did you 1.Spend or 2.Earn (input 1 or 2) : ");
        int n = 0;
        while(n == 0) {
            if (input.hasNextInt()) {
                int userInput = input.nextInt();
                if (userInput == 1) { //false is spent
                    budget = 1;
                    n = 1;
                } else if (userInput == 2) { //true is earned
                    budget = 2;
                    n = 1;
                } else {
                    System.out.println("INVALID INPUT - Enter a valid value: ");
                }
            } else {
                System.out.println("INVALID INPUT - Enter a valid value: ");
            }
        }
    }

    public static void TodaysFinance(int d, int m, int y) {
        DBConnect db = new DBConnect("entries.db");

        noEntries = true;
        totalEarned = 0;
        totalSpent = 0;

        ArrayList<Category> theEntries = db.getData();
        //System.out.println("getting data...");

        for(Category entry : theEntries) {
            if (entry.day == d) {
                if (entry.month == m ) {
                    if (entry.year == y) {
                        noEntries = false;
                        break;
                    }
                }
            }
        }

        if(noEntries == false) {
            System.out.printf("%-22s%-9s%-50s\n", "Category", "Amount", "Comment");
            for(Category entry : theEntries) {
                if(entry.day == d) {
                    if(entry.month == m ) {
                        if(entry.year == y) {
                            if(entry.budget == 1) { //spent
                                System.out.printf("%-22s%-11s%-20s\n", entry.catName, "- $" + entry.amount, entry.comment);
                                totalSpent = totalSpent + Float.valueOf(entry.amount);
                            }
                            else if(entry.budget == 2) { //earned
                                System.out.printf("%-22s%-11s%-20s\n", entry.catName, "+ $" + entry.amount, entry.comment);
                                totalEarned = totalEarned + Float.valueOf(entry.amount);
                            }
                        }
                    }
                }
            }
            System.out.printf("%-22s%-11s\n", "Total Spent", "$" + totalSpent);
            System.out.printf("%-22s%-11s\n", "Total Earned", "$" + totalEarned);
        }
        else if (noEntries) {
            System.out.println("Error: There are no existing entries.");
        }
    }

    public static void DaysFinance() {
        DBConnect db = new DBConnect("entries.db");

        noEntries = true;
        totalEarned = 0;
        totalSpent = 0;

        System.out.println("Enter the year (yy): ");
        Scanner input = new Scanner(System.in);
        year = input.nextInt();
        System.out.println("Enter the month (mm): ");
        month = input.nextInt();
        System.out.println("Enter the day(dd): ");
        day = input.nextInt();

        ArrayList<Category> theEntries = db.getData();
        //System.out.println("getting data...");

        for(Category entry : theEntries) {
            if (entry.day == day) {
                if (entry.month == month) {
                    if (entry.year == year) {
                        noEntries = false;
                        break;
                    }
                }
            }
        }

        if(noEntries == false) {
            System.out.printf("%-22s%-9s%-50s\n", "Category", "Amount", "Comment");
            for(Category entry : theEntries) {
                if (entry.day == day) {
                    if (entry.month == month) {
                        if (entry.year == year) {
                            if(entry.budget == 1) { //spent
                                System.out.printf("%-22s%-11s%-20s\n", entry.catName, "- $" + entry.amount, entry.comment);
                                totalSpent = totalSpent + Float.valueOf(entry.amount);
                            }
                            else if(entry.budget == 2) { //earned
                                System.out.printf("%-22s%-11s%-20s\n", entry.catName, "+ $" + entry.amount, entry.comment);
                                totalEarned = totalEarned + Float.valueOf(entry.amount);
                            }
                        }
                    }
                }
            }
            System.out.printf("%-22s%-11s\n", "Total Spent", totalSpent);
            System.out.printf("%-22s%-11s\n", "Total Earned", totalEarned);
        }
        else if (noEntries) {
            System.out.println("Error: There are no existing entries.");
        }
    }

    public static void MonthsFinance(int num, String[] cats) {
        DBConnect db = new DBConnect("entries.db");

        float [] earned = new float[num];
        float [] spent = new float[num];
        totalEarned = 0;
        totalSpent = 0;
        noEntries = true;

        System.out.println("Enter the year (yy): ");
        Scanner input = new Scanner(System.in);
        year = input.nextInt();
        System.out.println("Enter the month (mm): ");
        month = input.nextInt();

        System.out.printf("%-22s%-22s%-22s\n", " ", "Earned", "Spent");

        ArrayList<Category> theEntries = db.getData();
        for(Category entry : theEntries){
            for(int i = 0; i < num; i++) {
                if(entry.year == year) {
                    if(entry.month == month) {
                        if (entry.catNum == i+1) {
                            if (entry.budget == 2) {
                                earned[i] = earned[i] + Float.valueOf(entry.amount);
                                noEntries = false;
                            } else if (entry.budget == 1) {
                                spent[i] = spent[i] + Float.valueOf(entry.amount);
                                noEntries = false;
                            }
                        }
                    }
                }
            }
        }
        for (int j = 0; j < num; j++) {
            System.out.printf("%-22s%-22s%-22s\n", cats[j], "$" + earned[j], "$" + spent[j]);
        }
        for (int k = 0; k < num; k++) {
            totalEarned = totalEarned + earned[k];
            totalSpent = totalSpent + spent[k];
        }
        if(noEntries == false) {
            System.out.printf("%-22s%-22s%-22s\n", "Total", "$" + totalEarned, "$" + totalSpent);
            System.out.println("\nSpending Percentages");
            for(int m = 0; m < num; m++) {
                System.out.printf("%-15s%-6s\n", cats[m], ((spent[m]/totalSpent)*100) + "%");
            }
        }
        else if(noEntries == true) {
            System.out.println("Error: There are no existing entries.");
        }
    }

    public static void YearsFinance(int num, String[] cats) {
        DBConnect db = new DBConnect("entries.db");
        float[] spending = new float[num];

        float [] earned = new float[num];
        float [] spent = new float[num];
        totalEarned = 0;
        totalSpent = 0;
        noEntries = true;

        System.out.println("Enter the year (yy): ");
        Scanner input = new Scanner(System.in);
        year = input.nextInt();

        System.out.printf("%-22s%-22s%-22s\n", " ", "Earned", "Spent");

        ArrayList<Category> theEntries = db.getData();
        for(Category entry : theEntries){
            for(int i = 0; i < num; i++) {
                if(entry.year == year) {
                    if (entry.catNum == (i+1)) {
                        if (entry.budget == 2) {
                            earned[i] = earned[i] + Float.valueOf(entry.amount);
                            noEntries = false;
                        } else if (entry.budget == 1) {
                            spent[i] = spent[i] + Float.valueOf(entry.amount);
                            noEntries = false;
                        }
                    }
                }
            }
        }
        for (int j = 0; j < num; j++) {
            System.out.printf("%-22s%-22s%-22s\n", cats[j], "$" + earned[j], "$" + spent[j]);
        }
        for (int k = 0; k < num; k++) {
            totalEarned = totalEarned + earned[k];
            totalSpent = totalSpent + spent[k];
        }
        if(noEntries == false) {
            System.out.printf("%-22s%-22s%-22s\n", "Total", "$" + totalEarned, "$" + totalSpent);
            System.out.println("\nSpending Percentages");
            for(int m = 0; m < num; m++) {
                System.out.printf("%-15s%-6s\n", cats[m], ((spent[m]/totalSpent)*100) + "%");
            }
        }
        else if(noEntries == true) {
            System.out.println("Error: There are no existing entries.");
        }
    }

}
