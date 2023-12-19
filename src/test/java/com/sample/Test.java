package com.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {


    @org.testng.annotations.Test
    public void invokebrowser()
    {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("");


    }
}
