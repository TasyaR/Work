import java.util.Scanner;
import java.io.IOException;


public class Calculater {

    static char operation;
    static int result;

    // метод для перевода арабского числа в римскую систему
    public static String numberToRoman(int Index) {

        Index -= 1;

        String[] roman = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        String res = roman[Index];
        return res;

    }

    // метода для перевода римского числа в арабскую систему
    public static int romanToNumber(String roman) {
        switch (roman) {
            case "I":
                return 1;
            case "II":
                return 2;
            case "III":
                return 3;
            case "IV":
                return 4;
            case "V":
                return 5;
            case "VI":
                return 6;
            case "VII":
                return 7;
            case "VIII":
                return 8;
            case "IX":
                return 9;
            case "X":
                return 10;
            default:
                return -1;
        }
    }

    // Калькулируем - производим вычисления
    public static int calculate(int operand1, int operand2, char operation) {
        int result = 0;
        switch (operation) {
            case '+':
                return result = operand1 + operand2;
            case '-':
                return result = operand1 - operand2;
            case '*':
                return result = operand1 * operand2;
            case '/':
                try {
                    result = operand1 / operand2;
                } catch (ArithmeticException error) {
                    System.out.println("Исключение - деление на ноль ");
                    break;
                }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println("Введите выражение для вычисления, например 1+2 или II/I");

        // Считываем строку
        Scanner consoleIn = new Scanner(System.in);
        String Input = consoleIn.nextLine();
        consoleIn.close();

        // счётчик для определения кол-ва операций в Input
        int schet = 0;

        // находим знак операции в нашей строке
        for (int i = 0; i < Input.length(); i++) {
            if ("+/*-".indexOf(Input.charAt(i)) > -1) {
                operation = Input.charAt(i);
                schet += 1;
            }
        }

        String[] operands = Input.split("[+-/*]");

        // Проверяем на кол-во операндов и операций
        if (schet != 1 || operands.length != 2) {
            try {
                throw new IOException();
            } catch (IOException error) {
                System.out.println(
                        "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else {

            String operand1 = operands[0].trim();
            String operand2 = operands[1].trim();
            int number1 = romanToNumber(operand1);
            int number2 = romanToNumber(operand2);

            // Если числа не прошли проверку на "Римские", значит считаем, что они
            // "Арабские"
            if (romanToNumber(operand1) == -1 && romanToNumber(operand2) == -1) {
                number1 = Integer.parseInt(operand1);
                number2 = Integer.parseInt(operand2);
                result = calculate(number1, number2, operation);
                System.out.println("Результат вычисления: " + result);
            } else {
                if (romanToNumber(operand1) == -1 || romanToNumber(operand2) == -1) {
                    try {
                        throw new IOException();
                    } catch (IOException error) {
                        System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    }
                } else {
                    result = calculate(number1, number2, operation);
                    try {
                        String result1 = numberToRoman(result);
                        System.out.println("Результат вычисления: " + result1);
                    } catch (Exception error) {
                        System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                    }
                }
            }
        }
    }
}