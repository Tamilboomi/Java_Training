package com.tamilboomi;

import com.tamilboomi.models.User;

public class Main {

    public static void main(String[] args) {
        try {
            String responseData = DataScraper.getResponseFromPage(1);

            User[] usersToInsert = DataScraper.getUsersFromResponse(responseData);
            DatabaseHelper dbHelper = new DatabaseHelper();

            // for (int i=0;i<usersToInsert.length;i++) { <-- This can also be used to access the data
            for (User user : usersToInsert) {
                dbHelper.executeSQLQuery(
                        "INSERT INTO users values(" + user.getId()
                                + ",' " + user.getName()
                                + "','" + user.getEmail()
                                + "');");
            }
            dbHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
