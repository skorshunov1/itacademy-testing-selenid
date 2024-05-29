package com.itacademy;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PrinterTest {

    @Test
    public void firstTest(){
        Printer printer = new Printer();
        String result = printer.print(3);
        assertEquals(result,"Three");
    }

    @Test
    public void secondTest(){
        Printer printer = new Printer();
        String result = printer.print(5);
        assertEquals(result,"Five");
    }

    @Test
    public void thirdTest(){
        Printer printer = new Printer();
        String result = printer.print(7);
        assertEquals(result,"Seven");
    }
}
