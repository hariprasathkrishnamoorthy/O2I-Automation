package com.config;//package com.hchb.config;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    public static Properties prop;
//
//    public ConfigReader() {
//
//        prop = new Properties();
//        FileInputStream FP;
//
//
//
//        try {
//            FP = new FileInputStream(System.getProperty("user.dir")+"\\AutomationConfig\\Config.properties");
//        } catch (
//                FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            prop.load(FP);
//        } catch (
//                IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String getDriverPath(){
//        String driverPath = prop.getProperty("DriverPath");
//        if(driverPath!= null) return driverPath;
//        else throw new RuntimeException("DriverPath not specified in the Configuration file.");
//    }
//
//    public String getURL(){
//        String url = prop.getProperty("URL");
//        if(url!= null) return url;
//        else throw new RuntimeException("URL not specified in the Configuration file.");
//    }
//    public String getUsername(){
//        String username = prop.getProperty("Username");
//        if(username!= null) return username;
//        else throw new RuntimeException("Username not specified in the Configuration file.");
//    }
//
//    public String getPassword(){
//        String password = prop.getProperty("Password");
//        if(password!= null) return password;
//        else throw new RuntimeException("Password not specified in the Configuration file.");
//    }
//
//    public String getHCHBInstanceName(){
//        String hCHBInstanceName = prop.getProperty("HCHBInstanceName");
//        if(hCHBInstanceName!= null) return hCHBInstanceName;
//        else throw new RuntimeException("HCHBInstanceName not specified in the Configuration file.");
//    }
//
//    public String getStageFilter1(){
//        String stageFilter1 = prop.getProperty("StageFilter1");
//        if(stageFilter1!= null) return stageFilter1;
//        else throw new RuntimeException("StageFilter1 not specified in the Configuration file.");
//    }
//    public String getStageFilter2(){
//        String stageFilter2 = prop.getProperty("StageFilter2");
//        if(stageFilter2!= null) return stageFilter2;
//        else throw new RuntimeException("StageFilter2 not specified in the Configuration file.");
//    }
//    public String getScratchPadLimit(){
//        String scratchPadLimit = prop.getProperty("ScratchPadLimit");
//        if(scratchPadLimit!= null) return scratchPadLimit;
//        else throw new RuntimeException("ScratchPadLimit not specified in the Configuration file.");
//    }
//    public String getSPAUsername(){
//        String SPAUsername = prop.getProperty("SPAUsername");
//        if(SPAUsername!= null) return SPAUsername;
//        else throw new RuntimeException("SPAPassword not specified in the Configuration file.");
//    }
//    public String getSPAPassword(){
//        String SPAPassword = prop.getProperty("SPAPassword");
//        if(SPAPassword!= null) return SPAPassword;
//        else throw new RuntimeException("SPAPassword not specified in the Configuration file.");
//    }
//
//    public String getBaseURL(){
//        String BaseURL = prop.getProperty("BaseURL");
//        if(BaseURL!= null) return BaseURL;
//        else throw new RuntimeException("BaseURL not specified in the Configuration file.");
//    }
//
//    public String getEndPointURL(){
//        String EndPointURL = prop.getProperty("EndPointURL");
//        if(EndPointURL!= null) return EndPointURL;
//        else throw new RuntimeException("EndPointURL not specified in the Configuration file.");
//    }
//
//    }
