package com.example.doa;

import com.example.doa.greeter.Greeter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Greeter greeter = GreeterFactory.getGreeter(args[0]);
        System.out.println(greeter.sayHello());
    }
}
