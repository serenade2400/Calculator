package Calculator;

import java.util.Scanner;

public class BasicCalculator {
    public static void main(String[] args) {
        boolean continueCaluculation = true; // initialized to keep the calculator in a loop
        int trials = 5; // number of trials available for user input
        Scanner input = new Scanner(System.in); // to take user input
        while (continueCaluculation) { // loop to keep calculator running until user quits
            System.out.println("You have " + trials + " trials left"); // informs the user about remaining trials

            System.out.println("Enter the first number");
            double firstNumber = getNumber(input, trials); // getNumber function to get valid number input
            System.out.println("Enter the second number");
            double secondNumber = getNumber(input, trials); // getNumber called for the second number

            while (trials > 0) {
                System.out.print("Please enter the operation you want to perform:\n" +
                        "for addition : '+'\n" +
                        "for subtraction : '-' \n" +
                        "for multiplication : 'x' \n" +
                        "for division : '/'\n"
                );
                String operator = input.nextLine(); // operator input
                try {
                    switch (operator) {
                        case "+":
                            System.out.println(firstNumber + secondNumber + " is the output"); // addition
                            break;
                        case "-":
                            System.out.println(firstNumber - secondNumber + " is the output"); // subtraction
                            break;
                        case "x":
                            System.out.println(firstNumber * secondNumber + " is the output"); // multiplication
                            break;
                        case "/":
                            if (secondNumber == 0) {
                                trials--; // decrease trials if division by zero is attempted
                                throw new Exception("Division by zero is not allowed");
                            }
                            System.out.println(firstNumber / secondNumber + " is the output"); // division
                            break;
                        default:
                            System.out.println("Invalid operator"); // if an invalid operator is given
                            trials--; // decrease trials for invalid operator input
                            System.out.println("You have " + trials + " trials left");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage()); // exception handling for errors like division by zero
                    trials--; // decrease trials for errors
                    System.out.println("You have " + trials + " trials left");
                }
                // break the loop if the operation is valid and successful
                if (trials > 0 && (operator.equals("+") || operator.equals("-") || operator.equals("x") || operator.equals("/"))) {
                    break;
                }
            }

            // stop calculation if no trials left
            if (!(trials > 0)) {
                continueCaluculation = false;
            }

            // ask the user if they want to perform another calculation
            System.out.println("Do you want to perform another calculation? (yes/no)");
            trials = 5; // reset trials for the next round
            String confirm = input.nextLine();
            if (confirm.equals("yes")) {
                continueCaluculation = true; // continue if user says yes
            } else {
                continueCaluculation = false; // exit if user says no
                System.out.println("Exiting calculator!!");
            }
        }
    }

    public static double getNumber(Scanner input, int trials) { // function to get valid number input
        double number = 0;
        while (trials > 0) {
            try {
                number = input.nextDouble(); // try to get valid number
                input.nextLine(); // consume newline left by nextDouble()
                break;
            } catch (Exception e) {
                trials--; // decrease trials for invalid input
                System.out.println("You have " + trials + " trials left");
                System.out.println("Enter a valid number");
                input.next(); // consume invalid input
            }
        }
        return number; // return the valid number
    }
}
