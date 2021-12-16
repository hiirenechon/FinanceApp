package com.company.controllers;

import com.company.views.CmdLineView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;

public class Main {

    public static int choice;
    public static int cat;
    public static int numOfCats = 0;
    public static String catName;
    public static String date;
    public static String amountString;
    public static int day;
    public static int month;
    public static int year;

    public static void main(String[] args) {
        CmdLineView view = new CmdLineView();
        DBConnect db = new DBConnect("entries.db");

        date = SetTodaysDate();
        day = Day();
        month = Month();
        year = Year();

        view.CmdLineView(date);

        String[] cats = new String[10]; //set limit on # of categories to 10

        GetFile file = new GetFile();
        file.GetFile();
        if(file.exists == true) {
            //System.out.println("Adding existing categories");
            int n = 0;
            while (file.categories[n] != null) {
                cats[n] = file.categories[n];
                n++;
                numOfCats++;
                //System.out.println("added " + n);
            }
        }

        db.createNewDatabase();
        db.addTables();

        int i = 0;
        while(i == 0) {
            choice = view.MainMenu();
            if(choice == 1){ //add entry
                cat = view.ChooseCat(numOfCats, cats);
                if(cat == numOfCats + 1) //make new category
                {
                    numOfCats++;
                    cats[numOfCats - 1] = view.MakeNewCat(numOfCats, cats);
                    file.AddToFile(cats[numOfCats - 1]);
                } //else add entry to existing category
                view.GetInfo();
                catName = cats[cat - 1];
                amountString = String.valueOf(view.amount);
                db.addData(cat, catName, view.comment, amountString, day, month, year, view.budget);
            }
            else if(choice == 2) { //view entries
                choice = view.ViewMenu();
                if(choice == 1) {
                    //view today's finance
                    view.TodaysFinance(day, month, year);
                }
                else if (choice == 2) {
                    //choose day to view
                    view.DaysFinance();
                }
                else if (choice == 3) {
                    //choose month to view
                    System.out.println(numOfCats);
                    view.MonthsFinance(numOfCats, cats);
                }
                else if (choice == 4) {
                    //choose year to view
                    view.YearsFinance(numOfCats, cats);
                }
            }
            else if(choice == 3) { //end program
                System.exit(0);
            }
        }
    }

    public static String SetTodaysDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        String currentDateFormatted = dateFormat.format(currentDate);
        return currentDateFormatted;
    }

    public static int Day() {
        LocalDate currentDate = LocalDate.now();
        int d = currentDate.getDayOfMonth();
        return d;
    }

    public static int Month() {
        LocalDate currentDate = LocalDate.now();
        int m = currentDate.getMonthValue();
        return m;
    }

    public static int Year() {
        int y = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return y;
    }
}
