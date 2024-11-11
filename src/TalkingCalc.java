import java.util.Scanner;

public class TalkingCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your expression below: ");

        String input = scanner.nextLine();
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            System.out.println("Invalid input format. Please enter in the format: number operator number");
            return;
        }

        int num1 = Integer.parseInt(parts[0]);
        String operator = parts[1];
        int num2 = Integer.parseInt(parts[2]);

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    System.out.println("Error: Division by zero");
                    return;
                }
                result = num1 / num2;
                break;
            case "%":
                result = num1 % num2;
                break;
            default:
                System.out.println("Invalid operator. Please use +, -, *, /, or %.");
                return;
        }

        System.out.println("## [" + numberToWords(num1) + "] [" + operatorToWord(operator) + "] [" + numberToWords(num2) + "] [equals] [" + numberToWords(result) + "]");
    }

    public static String numberToWords(int num) {
        if (num == 0) {
            return "zero";
        }
        if (num < 0) {
            return "negative " + numberToWords(-num);
        }

        String[] thousands = {
                "", "thousand", "million", "billion"
        };

        StringBuilder words = new StringBuilder();

        int thousandIndex = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                words.insert(0, convertBelowThousand(num % 1000) + thousands[thousandIndex] + " ");
            }
            num /= 1000;
            thousandIndex++;
        }

        return words.toString().trim();
    }

    public static String convertBelowThousand(int num) {
        String[] belowTwenty = {
                "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                "seventeen", "eighteen", "nineteen"
        };
        String[] tens = {
                "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
        };

        if (num == 0) {
            return "";
        } else if (num < 20) {
            return belowTwenty[num] + " ";
        } else if (num < 100) {
            return tens[num / 10] + " " + convertBelowThousand(num % 10);
        } else {
            return belowTwenty[num / 100] + " hundred " + convertBelowThousand(num % 100);
        }
    }

    public static String operatorToWord(String operator) {
        switch (operator) {
            case "+":
                return "plus";
            case "-":
                return "minus";
            case "*":
                return "times";
            case "/":
                return "divided by";
            case "%":
                return "modulo";
            default:
                return "unknown";
        }
    }
}