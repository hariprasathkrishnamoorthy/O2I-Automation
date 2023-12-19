package com.managers;

import com.config.JsonConfigReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FileReaderManager {


    private static JsonConfigReader jsonConfigReader;



    public static JsonConfigReader getJsonConfigReader() throws IOException, ParseException {
        return (jsonConfigReader == null) ? new JsonConfigReader() : jsonConfigReader;
    }


    public static JsonConfigReader getJsonConfigReader(String filename) throws IOException, ParseException {
        return (jsonConfigReader == null) ? new JsonConfigReader(filename) : jsonConfigReader;
    }

}

