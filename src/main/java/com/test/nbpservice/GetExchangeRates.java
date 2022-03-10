package com.test.nbpservice;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Slf4j
@Service
public class GetExchangeRates {

    public String getExchangeRates(String currencyCode) {
        try {
            // create connection
            URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/a/" + currencyCode + "/last/5/?format=json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            StringBuilder line = new StringBuilder();

            // check for error messages
            if (connection.getResponseMessage().equals("404")) {
                line.append("The current table has not been published yet!");
                return String.valueOf(line);

            } else if (!connection.getResponseMessage().equals("OK")) {
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

            // create JSON array and read daily rates
            JSONObject obj = new JSONObject(String.valueOf(line));
            JSONArray rates = obj.getJSONArray("rates");

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < rates.length(); i++) {
                JSONObject rate = rates.getJSONObject(i);
                String date = rate.getString("effectiveDate");
                double priceVal = rate.getDouble("mid");
                result.append(date + " : " + priceVal + "; ");
            }

            return "Currency exchange rate PLN to " + currencyCode + ": " + result;

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
