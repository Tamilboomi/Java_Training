package com.tamilboomi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataScraper {

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

    public static void writeToFile(String dataToWrite, String fileName) {
        try {
            FileWriter fileToWrite = new FileWriter(fileName);
            fileToWrite.write(dataToWrite);
            fileToWrite.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String getReportTextFromResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        StringBuilder sb = new StringBuilder();
        sb.append("<table style='border: 1px solid black;'>");
        for (int i = 0; i < dataArray.length(); i++) {
            int id = dataArray.getJSONObject(i).getInt("id");
            String email = dataArray.getJSONObject(i).getString("email");
            String firstName = dataArray.getJSONObject(i).getString("first_name");
            String lastName = dataArray.getJSONObject(i).getString("last_name");
            String avatar = dataArray.getJSONObject(i).getString("avatar");

            sb.append("<tr>");
            sb.append("<td>").append("ID: ").append(id).append("</td>");
            sb.append("<td>").append("EMAIL: ").append(email).append("</td>");
            sb.append("<td>").append("NAME: ").append(firstName).append(" ").append(lastName).append("</td>");
            sb.append("<td>").append("PICTURE: ").append(avatar).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }
}
