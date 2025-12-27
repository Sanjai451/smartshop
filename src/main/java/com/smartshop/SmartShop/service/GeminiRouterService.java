package com.smartshop.SmartShop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.smartshop.SmartShop.dto.GeminiFunctionCall;
import com.smartshop.SmartShop.dto.GeminiFunctionResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

class ParsedCommand {

    private String function;
    private Map<String, Object> args;

    public ParsedCommand(String function, Map<String, Object> args) {
        this.function = function;
        this.args = args;
    }

    public String getFunction() {
        return function;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "ParsedCommand{" +
                "function='" + function + '\'' +
                ", args=" + args +
                '}';
    }
}


@Service
public class GeminiRouterService {
    Client client;
    private String API_KEY = "AIzaSyAsR5DWmpQ245sL56ui-JpLWK5tDCHRERc";

    public GeminiRouterService(){
        this.client = Client.builder().apiKey(API_KEY).build();
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public GeminiFunctionResponse route(String userQuery) throws Exception {
        String prompt = """
            You are an API router.
            
            Choose ONE backend function from the list below.
            
            Return ONLY a single JSON object with SIMPLE key-value pairs.
            DO NOT use nested objects.
            DO NOT add explanations or text.
            
            Available backend functions:
            1. searchByKeyword(keyword)
            2. searchByKeywordAndMaxPrice(keyword, maxPrice)
            3. searchByCategory(category)
            4. searchByPriceRange(minPrice, maxPrice)
            
            JSON format rules:
            - Always include "function"
            - Include only required parameters
            - Use numbers for price values
            
            Example output:
            {
              "function": "searchByKeywordAndMaxPrice",
              "keyword": "shoes",
              "maxprice": 300
            }
            
            User query: "%s"
            """.formatted(userQuery);


        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null);

//        System.out.println(response.text());
        int len = response.text().length();

        String s = response.text().substring(7, len-3);
        System.out.println(s);


        ObjectMapper mapper = new ObjectMapper();

        GeminiFunctionResponse r =
                mapper.readValue(s, GeminiFunctionResponse.class);

        System.out.println("r : " + r);


        return r;

    }
    public static ParsedCommand parse(String json) {

        JSONObject root = new JSONObject(json);

        String function = root.getString("function");

        JSONObject argsJson = root.getJSONObject("arguments");

        Map<String, Object> args = new HashMap<>();
        for (String key : argsJson.keySet()) {
            args.put(key, argsJson.get(key));
        }

        return new ParsedCommand(function, args);
    }
}
