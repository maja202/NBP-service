package com.test.nbpservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GetGoldPrice {

    public String getGoldPrice() {
        try {
            // create connection
            URL url = new URL("http://api.nbp.pl/api/cenyzlota/last/14/?format=json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            StringBuilder line = new StringBuilder();

            // check for error messages
            if (!connection.getResponseMessage().equals("OK")) {
                line.append("Something went wrong.");
                return String.valueOf(line);
            }

            InputStream response = connection.getInputStream();

            // read from stream
            Scanner scan = new Scanner(response);

            while (scan.hasNext()) {
                line.append(scan.nextLine());
            }

            scan.close();

            // create JSON array and calculate average gold price
            JSONArray prices = new JSONArray(String.valueOf(line));

            double sum = 0.0;

            for (int i = 0; i < prices.length(); i++) {
                JSONObject price = prices.getJSONObject(i);
                double priceVal = price.getDouble("cena");
                sum += priceVal;
            }

            double m = (double) Math.round((sum / prices.length()) * 100d) / 100d;

            return "Average gold price for the last 14 business days (in PLN): " + m;


        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }

}

