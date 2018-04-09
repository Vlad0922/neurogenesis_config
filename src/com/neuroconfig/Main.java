package com.neuroconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    static String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


    public static void main(String[] args) {
        try {
            String txt = readFile("config_example.xml", Charset.defaultCharset());
            XmlParser parser = new XmlParser(txt);

            Map xmlMap = parser.parseXML();


            List actions = (List)((HashMap)xmlMap.get("Pipeline")).get("Action");

            NeurogenesisPipeline p = new NeurogenesisPipeline(actions);
            p.run();

        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
