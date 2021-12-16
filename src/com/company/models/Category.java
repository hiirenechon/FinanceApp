package com.company.models;

public class Category {

    private int id;
    public static int catNum;
    public static String catName;
    public static String comment;
    public static String amount;
    public static int budget;
    public static int day;
    public static int month;
    public static int year;


    public Category(int id, int catNum, String catName, String comment, String amount, int day, int month, int year, int budget) {
        this.id = id;
        this.catNum = catNum;
        this.catName = catName;
        this.comment = comment;
        this.amount = amount;
        this.day = day;
        this.month = month;
        this.year = year;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatNum() {
        return catNum;
    }

    public void setCatNum(int catNum) {
        this.catNum = catNum;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int geMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
