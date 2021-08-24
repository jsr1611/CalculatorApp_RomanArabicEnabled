package com.calculator;

import java.util.Scanner;

public class Main {

    private static int num1, num2;
    private static String operation;
    private static double result;

    public static void main(String[] args) throws Exception {
	// write your code here
        Scanner console = new Scanner(System.in);
while (true) {
    System.out.println("Please, enter below an operation for calculation.");
    operation = console.nextLine();
    try {
        Operation op = new Operation(operation);
        System.out.println(operation + " = " + (op.getResult()));
        break;
    } catch (Exception e) {
        System.out.println("Error: " + e);
    }
}



    }
}
