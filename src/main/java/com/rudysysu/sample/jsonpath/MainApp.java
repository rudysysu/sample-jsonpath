package com.rudysysu.sample.jsonpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class MainApp {
    public static void main(String[] args) throws IOException {
        StringBuffer sb = new StringBuffer();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(MainApp.class.getClassLoader().getResourceAsStream("measure.json")))) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        }
        String json = sb.toString();
        DocumentContext context = JsonPath.parse(json);

        SpelExpressionParser parser = new SpelExpressionParser();

        {
            String jsonpath = "$.id";
            Object value = parser
                    .parseExpression("'" + context.read(jsonpath) + "'=='68361dc2-35d2-4250-acc5-0b4c388e781f'")
                    .getValue();
            System.out.println(value);
        }
        
        {
            String jsonpath = "$.smartObjects[?(@.smartObjectTypeKey==3336)].resources[?(@.resourceTypeKey==5513 && @.value > 10)]";
            Object read = context.read(jsonpath);
            System.out.println(read);
        }

        {
            String jsonpath = "$.smartObjects[?(@.smartObjectTypeKey==3336)].resources[?(@.resourceTypeKey==5513 && @.value > 10)]";
            Object value = parser.parseExpression(context.read(jsonpath)).getValue();
            System.out.println(value);
        }
    }
}