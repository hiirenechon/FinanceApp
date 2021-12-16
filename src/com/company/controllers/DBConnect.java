package com.company.controllers;

import com.company.models.Category;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {

    private String dbName;
    public String url;

    public DBConnect(String dbName) {
        this.dbName = dbName;
        this.url = "jdbc:sqlite:/Users/irenechon/sqlite/db/" + dbName;
    }

    public void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                //System.out.println("The driver name is " + meta.getDriverName());
                //System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTables() {

        String sql = "CREATE TABLE IF NOT EXISTS entries (\n"
                + "	id integer PRIMARY KEY,\n"
                + " catNum integer NOT NULL,\n"
                + "	catName text NOT NULL,\n"
                + "	comment text NOT NULL,\n"
                + " amount text NOT NULL,\n"
                + " day integer NOT NULL,\n"
                + " month integer NOT NULL,\n"
                + " year integer NOT NULL,\n"
                + " budget boolean NOT NULL\n"
                + ");";

        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
            //System.out.println("Tables added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addData(int catNum, String catName, String comment, String amount, int day, int month, int year, int budget) {

        String sql = "INSERT INTO entries(catNum, catName, comment, amount, day, month, year, budget) VALUES ('" + catNum + "', '" + catName + "', '" + comment + "', '" + amount + "', '" + day + "', '" + month + "', '" + year + "', '" + budget + "');";
        //System.out.println(sql);
        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            statement.execute(sql);
            //System.out.println("Data added");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Category> getData() {
        String sql = "SELECT id, catNum, catName, comment, amount, day, month, year, budget FROM entries";
        ArrayList<Category> entryList = new ArrayList<Category>();

        try(Connection conn = DriverManager.getConnection(url)){
            Statement statement = conn.createStatement();
            ResultSet entries = statement.executeQuery(sql);
            while(entries.next())
            {
                int id = entries.getInt("id");
                int catNum = entries.getInt("catNum");
                String catName = entries.getString("catName");
                String comment = entries.getString("comment");
                String amount = entries.getString("amount");
                int day = entries.getInt("day");
                int month = entries.getInt("month");
                int year = entries.getInt("year");
                int budget = entries.getInt("budget");
                Category entry = new Category(id, catNum, catName, comment, amount, day, month, year, budget);

                entryList.add(entry);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return entryList;
    }
}
