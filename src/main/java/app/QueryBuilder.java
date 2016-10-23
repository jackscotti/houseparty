package app;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class QueryBuilder {

    final String ROOT_URL;
    final String SEARCH_PARAMS_PATH;
    final String KEY_PATH;

    public QueryBuilder(){
        ROOT_URL = "http://api.zoopla.co.uk/api/v1/property_listings.json?";
        SEARCH_PARAMS_PATH = "./src/main/resources/search_parameters.yml";
        KEY_PATH = "./src/main/resources/.secrets.yml";
    }

    public String build() throws IOException {

        ArrayList<String> queryParts = new ArrayList<>();

        Iterator<HashMap.Entry<String, String>> entries = params().entrySet().iterator();
        while (entries.hasNext()) {
            HashMap.Entry<String, String> entry = entries.next();

            queryParts.add(entry.getKey() + "=" + entry.getValue());
        }

        return ROOT_URL + String.join("&", queryParts);
    }

    public HashMap params() throws IOException {
        YamlReader reader = new YamlReader(new FileReader(SEARCH_PARAMS_PATH));
        HashMap<String, String> searchParams = (HashMap) reader.read();

        reader = new YamlReader(new FileReader(KEY_PATH));
        HashMap<String, String> key = (HashMap) reader.read();

        reader.close();

        HashMap<String, String> allParams = new HashMap<>();

        allParams.putAll(searchParams);
        allParams.putAll(key);

        return allParams;
    }
}
