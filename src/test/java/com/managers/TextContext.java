package com.managers;

public class TextContext {

    private WebDriverManagercust webDriverManager;
    private PageObjectManager pageObjectManager;

    private ScreenObjectManager screenObjectManager;

    public TextContext(){

        webDriverManager = new WebDriverManagercust();
        screenObjectManager = new ScreenObjectManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getWebdriver(), screenObjectManager.getScreenObject());

    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

}
