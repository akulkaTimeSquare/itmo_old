package ru.itmo;

public class ExceptionThrower {
    public static void throwError() throws ArithmeticException {
        System.out.println(1/0);
    }
}
