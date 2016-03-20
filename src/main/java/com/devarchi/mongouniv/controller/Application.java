package com.devarchi.mongouniv.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

/**
 * Created by donghoon on 2016. 3. 21..
 */
public class Application {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(Application.class, "/");

        post("favorite_fruit", (request1, response1) -> {
            final String fruit = request1.queryParams("fruit");

            if (fruit == null)
                return "why don't you pick one?";
            else
                return "Your favorite fruit is " + fruit;
        });

        get("/", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                Template template = configuration.getTemplate("fruitsPicker.ftl");
                Map<String, Object> fruitsMap = new HashMap<>();
                fruitsMap.put("fruits", Arrays.asList("banana", "peach", "orange", "apple"));

                template.process(fruitsMap, writer);

                System.out.println(writer);
            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }

            return writer;
        });

        get("/echo/:thing", (request, response) -> request.params(":thing"));
    }
}
