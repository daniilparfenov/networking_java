package org.example;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] urls = {
                "https://fake-json-api.mock.beeceptor.com/companies",
                "https://fake-json-api.mock.beeceptor.com/users",
                "https://dummy-json.mock.beeceptor.com/todos",
                "https://dummy-json.mock.beeceptor.com/posts",
                "https://dummy-json.mock.beeceptor.com/continents"
        };

        try {
            URLContentParser parser = new URLContentParser(urls[0]);
            for (String url : urls) {
                parser.setUrl(url);
                ArrayList<Map<String, Object>> parsedDataFromURL = parser.getData();
                System.out.println("Data from " + url);
                for (Map<String, Object> jsonMap : parsedDataFromURL) {
                    for (Map.Entry<String, Object> jsonMapElement : jsonMap.entrySet()) {
                        System.out.println(jsonMapElement.getKey() + ": " + jsonMapElement.getValue());
                    }
                    System.out.println("-".repeat(160));
                }
                System.out.println();
            }
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL");
        } catch (IOException e) {
            System.out.println("Parsing error");
        }
    }
}
