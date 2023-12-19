package com.managers;

public class TextContext {

    private WebDriverManager webDriverManager;
    private PageObjectManager pageObjectManager;

    private ScreenObjectManager screenObjectManager;

    public TextContext(){

        webDriverManager = new WebDriverManager();
        screenObjectManager = new ScreenObjectManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getWebdriver(), screenObjectManager.getScreenObject());

    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

}
