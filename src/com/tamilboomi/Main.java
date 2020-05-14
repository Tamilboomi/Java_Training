package com.tamilboomi;

public class Main {

    public static void main(String[] args) {
        try {
            String responseData = DataScraper.getResponse("https://reqres.in/api/users?page=2");
            DataScraper.writeToFile(DataScraper.getReportTextFromResponse(responseData),"file.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
