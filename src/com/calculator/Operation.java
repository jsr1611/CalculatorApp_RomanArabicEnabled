package com.calculator;

public class Operation {
    private double result;
    private double num1, num2;

    private final int MUL = 1, DIV = 2, ADD = 3, SUB = 4;
    private final int[] arabicNumerals = {1000, 500, 100, 50, 10, 5, 1};
    private final String[] romanNumerals = {"M", "D", "C", "L", "X", "V", "I"};

    public Operation() {
    }

    /*
    do math operation based on given string line
     */
    public Operation(String operationString) throws Exception {
        int operation = getOperation(operationString);
        switch (operation) {
            case MUL:
                result = Multiply(num1, num2);
                break;
            case DIV:
                result = Divide(num1, num2);
                break;
            case ADD:
                result = Addition(num1, num2);
                break;
            case SUB:
                result = Subtract(num1, num2);
                break;
            default:
                throw new Exception("Wrong operation was given.");
        }


    }

    private int getOperation(String operation) {
        int operationNum = -1;
        if (operation.isEmpty())
            return operationNum;

        String[] values = operation.strip().split(" ");
        num1 = getNum(values[0]);
        num2 = getNum(values[2]);

        switch (values[1]) {
            case "*":
                operationNum = MUL;
                break;
            case "/":
                operationNum = DIV;
                break;
            case "+":
                operationNum = ADD;
                break;
            case "-":
                operationNum = SUB;
                break;
            default:
                operationNum = -1;

        }
        return operationNum;
    }

    private double getNum(String value) {
        double num;
        try {
            num = Double.parseDouble(value);
        } catch (Exception e) {
            num = processRomeNumbers(value);
        }
        return num;
    }

    /*
    Convert roman numerals to arabic ones.
     */
    private double processRomeNumbers(String value) {
        int result = 0;
        int correspondingNum = 0;
        int numberOnTheRight = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            if (String.valueOf(value.charAt(i)).isEmpty())
                continue;
            try {
                correspondingNum = arabicNumerals[getIndex(value.charAt(i))];
            } catch (IndexOutOfBoundsException e) {
                throw new ArithmeticException("Please enter valid numbers, either arabic or roman numerals are valid.");
            }

            if (i < value.length() - 1) {
                result = numberOnTheRight <= correspondingNum ? result + correspondingNum : result - correspondingNum;
            } else {
                result = correspondingNum;
            }
            numberOnTheRight = correspondingNum;


        }

        return result;
    }


    /*
    Get the index of roman numeral value in arabic numerals list.
     */
    private int getIndex(char romanNumeral) {
        for (int i = 0; i < romanNumerals.length; i++) {
            if (String.valueOf(romanNumeral).equals(romanNumerals[i])) {
                // System.out.println("index is " + i + "for "+romanNumeral + "is equal to " + romanNumerals[i]);
                return i;
            }

        }
        // exception case
        return -1;
    }

    public double getResult() {
        return result;
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


}
