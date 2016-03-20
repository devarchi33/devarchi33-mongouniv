package com.devarchi.mongouniv.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
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

        get("/", (request, response) -> {
            StringWriter writer = new StringWriter();

            try {
                Template template = configuration.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "spark with freemarker");

                template.process(helloMap, writer);

                System.out.println(writer);
            } catch (Exception e) {
                halt(500);
                e.printStackTrace();
            }

            return writer;
        });
    }
}
