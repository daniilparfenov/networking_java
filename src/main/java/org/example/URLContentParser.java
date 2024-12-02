package org.example;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringEscapeUtils;

public class URLContentParser {
    private URL url;
    private final Gson gson;

    public URLContentParser(String url) throws MalformedURLException {
        this.url = new URL(url);
        this.gson = new Gson();
    }

    public ArrayList<Map<String, Object>> getData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder response = new StringBuilder();
        String line = "";

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Некоторые символы могут не декодироваться, из-за чего могут появиться &#x27; вместо апострофов, например
        // Поэтому декодируем HTML-закодированные символы, если таковые имеются
        String decodedResponse = StringEscapeUtils.unescapeHtml4(response.toString());

        Type listOfMapsType = new TypeToken<ArrayList<Map<String, Object>>>() {
        }.getType();
        return gson.fromJson(decodedResponse, listOfMapsType);
    }

    public void setUrl(String url) throws MalformedURLException {
        this.url = new URL(url);
    }
}
