package com.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class WebDriverManagercust {

    private static WebDriver driver;

    private WebDriver createWebDriver(){
        //System.setProperty("webdriver.chrome.driver", "D:\\Users\\hariprasath.k\\IdeaProjects\\HCHB\\driver\\chromedriver.exe");

        WebDriverManager.chromedriver().setup();

         driver = new ChromeDriver();

         driver.manage().window().maximize();

        return driver;

    }

    public WebDriver getWebdriver(){

        if(driver == null) driver = createWebDriver();
        return driver;
    }



}

