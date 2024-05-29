package com.itacademy;

public class Printer {


    public String print(int a) {
       StringBuilder stringBuilder = new StringBuilder();

            if (a % 3 == 0)
                stringBuilder.append("Three");
            if (a % 5 == 0)
                stringBuilder.append("Five");
            if (stringBuilder.isEmpty())
                stringBuilder.append(a);
                return stringBuilder.toString();
    }
}
