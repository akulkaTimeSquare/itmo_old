package ru.itmo;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ErrorThrower errorThrower1 = new ErrorThrower();
        ErrorThrower errorThrower2 = new ErrorThrower();
        ExceptionThrower exceptionThrower = new ExceptionThrower();
        System.out.println(errorThrower1.getClass());
        System.out.println(errorThrower2.getClass() == errorThrower1.getClass());
        System.out.println(exceptionThrower.getClass() == errorThrower1.getClass());
    }
}
