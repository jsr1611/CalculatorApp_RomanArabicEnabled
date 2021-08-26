package com.calculator;

import java.util.Scanner;

public class Main {

    private static int num1, num2;
    private static String operation;
    private static double result;
    private static String printMsg = "";

    public static void main(String[] args) throws Exception {
        // write your code here
        printMsg = "Please, enter below an operation for calculation.";
        Scanner console = new Scanner(System.in);
        while (true) {

                System.out.println(printMsg);
            operation = console.nextLine();
            try {
                Operation op = new Operation(operation);
                op.printResult();
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }

            if (!printMsg.contains("again")){
                printMsg = printMsg.replace("."," ");
                printMsg = printMsg.concat("once again.");
            }
        }


    }
}
