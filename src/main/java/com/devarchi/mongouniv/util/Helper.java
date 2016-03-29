package com.devarchi.mongouniv.util;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;

/**
 * Created by donghoon on 2016. 3. 30..
 */
public class Helper {

    public static void printJson(Document document) {
        JsonWriter writer = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL, false));

        new DocumentCodec().encode(writer, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());

        System.out.println(writer.getWriter());
        System.out.flush();
    }
}
