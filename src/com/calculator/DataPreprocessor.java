package com.calculator;

public class DataPreprocessor {
    private final int[] arabicNumerals = {100,90,50,40,10,9,5,4,1};
    private final String[] romanNumerals = {"C","XC","L","XL","X","IX","V","IV","I"};
    private double num1, num2;
    private boolean isNum1_roman, isNum2_roman;

    public DataPreprocessor(){}


    /*
    Return number in the "double" format
     */
    private double getNum(String value, int id) {
        double num;
        try {
            num = Double.parseDouble(value);
        } catch (Exception e) {

            if (id == 1 && value.matches("(X|IX|IV|V|VI|VII|VIII|I|II|III)"))
                isNum1_roman = true;
            else if (id == 2 && value.matches("(X|IX|IV|V|VI|VII|VIII|I|II|III)"))
                isNum2_roman = true;
            //else
            //System.out.println(value + " is not valid a roman number!");
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



    /*
    Check if numbers were given in roman literals
     */
    public boolean isRoman(){
        return isNum1_roman && isNum2_roman;
    }

    /*
    Return a roman numeral of the arabic number
     */
    public String getRomanNum(double input) {
        int number = (int)input;
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < arabicNumerals.length; i++) {
            while (number >= arabicNumerals[i]) {
                number -= arabicNumerals[i];
                roman.append(romanNumerals[i]);
            }
        }
        //System.out.println("Resulting roman: " + roman);
        return roman.toString();
    }

    public double[] getNumbers() {
        return new double[]{num1, num2};
    }

    /*
    Find out the operation type and extract numbers where arabic or roman.
     */
    public int getOperationNum(String[] values) throws Exception {
        num1 = getNum(values[0], 1);
        num2 = getNum(values[2], 2);
        String operation = values[1];
        int operationNum;
        if (((!isNum1_roman && !isNum2_roman) || (isNum1_roman && isNum2_roman)) && (0 <= num1 && num1 <= 10 && 0 <= num2 && num2 <= 10)) {
            int MUL = 1;
            int DIV = 2;
            int ADD = 3;
            int SUB = 4;
            int MOD = 5;
            switch (operation) {
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
                case "%":
                    operationNum = MOD;
                    break;
                default:
                    operationNum = -1;

            }

        } else
            throw new Exception("Please, enter either roman or arabic numbers between 0 - 10 or I - X");


        return operationNum;
    }

}
