package com.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.Screen;

public class ScreenObjectManager {

    private Screen screen;

    private Screen createScreenObject(){

        screen = new Screen();

        return screen;

    }

    public Screen getScreenObject(){

        if(screen == null) screen = createScreenObject();
        return screen;
    }

}
