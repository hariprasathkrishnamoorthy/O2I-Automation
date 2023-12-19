package com.sample;

public class Testclass
{
    static Testcontext testcontext;
    Testclass2 testclass2;
   public Testclass(Testcontext tx)
    {
       this.testcontext = tx;
    }

public static void main(String args[])
{
    testcontext.getTestclass();
}
}
