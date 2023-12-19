package com.utility;

import org.sikuli.script.Pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UtilityClass {




    public void WaitUntilImageAppear(Pattern ImgPath, int MilliSeconds){

        synchronized (ImgPath) {
            try {
                ImgPath.wait(MilliSeconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }





}
