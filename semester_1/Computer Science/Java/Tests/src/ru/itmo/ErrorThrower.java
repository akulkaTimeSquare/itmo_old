package ru.itmo;

public class ErrorThrower {
    public static void throwError() throws ArithmeticException {
        System.out.println(1/0);
    }
}

