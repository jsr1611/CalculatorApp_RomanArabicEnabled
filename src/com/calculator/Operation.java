package com.calculator;

public class Operation {

    private final int MUL = 1, DIV = 2, ADD = 3, SUB = 4, MOD = 5;
    private DataPreprocessor dp;
    private double result;


    public Operation() {
    }

    /*
    do math operation based on given string line
     */
    public Operation(String operationString) throws Exception {
        dp = new DataPreprocessor();

        int operation = getOperation(operationString);

        double[] numbers = dp.getNumbers();

        switch (operation) {
            case MUL:
                result = Multiply(numbers[0], numbers[1]);
                break;
            case DIV:
                result = Divide(numbers[0], numbers[1]);
                break;
            case ADD:
                result = Addition(numbers[0], numbers[1]);
                break;
            case SUB:
                result = Subtract(numbers[0], numbers[1]);
                break;
            case MOD:
                result = Modulo(numbers[0], numbers[1]);
                break;
            default:
                throw new Exception("Wrong operation was given. Please, try again with the below format.\nExample Input:\nn + m\nn * m\nn * m\nn / m");
        }
    }


    private int getOperation(String operation) throws Exception {
        int operationNum = -1;
        if (operation.isEmpty())
            return operationNum;

        String[] values = operation.strip().split(" ");
        operationNum = dp.getOperationNum(values);

        // Check if valid input was given
        if(values.length < 3)
            return -1;
        return operationNum;
    }


    /*
    Multiplication method
     */
    private double Multiply(double num1, double num2) {
        return num1 * num2;
    }

    /*
    Division method
     */
    private double Divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("The divisor (number 2) cannot be equal to 0!");
            throw new ArithmeticException();
        }
        return num1 / num2;
    }

    /*
    Addition method
     */
    private double Addition(double num1, double num2) {
        return num1 + num2;
    }

    /*
    Subtraction method
     */
    private double Subtract(double num1, double num2) {
        return num1 - num2;
    }

    /*
    Modulo method
     */
    private double Modulo(double num1, double num2) {
        if(num1>num2)
            return (num1 / (int)num2);
        else
            return 0;
    }

    /*
    Print the result to the screen
     */
    public void printResult() {
        if(dp.isRoman())
        {
            System.out.println(dp.getRomanNum(result));
        }
        else
            System.out.printf("%.2f%n", result);
    }
}
