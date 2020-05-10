package com.tamilboomi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataScraper {

    // Get data from API
    public static String getResponse(String fetchUrl) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(fetchUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    // Parse the Data using JSON parsing library
    public static String getReportTextFromResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dataArray.length(); i++) {
            int id = dataArray.getJSONObject(i).getInt("id");
            String email = dataArray.getJSONObject(i).getString("email");
            String firstName = dataArray.getJSONObject(i).getString("first_name");
            String lastName = dataArray.getJSONObject(i).getString("last_name");
            String avatar = dataArray.getJSONObject(i).getString("avatar");

            sb.append("\n").append("ID: ").append(id).append("\n");
            sb.append("EMAIL: ").append(email).append("\n");
            sb.append("NAME: ").append(firstName).append(" ").append(lastName).append("\n");
            sb.append("PICTURE: ").append(avatar).append("\n");
            sb.append("--------------------------------------------");
        }
        return sb.toString();
    }

    // Prepare the report and write to a file - sample.txt
    public static void writeToFile(String dataToWrite, String fileName) throws IOException {
        FileWriter fileToWrite = new FileWriter(fileName);
        fileToWrite.write(dataToWrite);
        fileToWrite.close();
    }

}
