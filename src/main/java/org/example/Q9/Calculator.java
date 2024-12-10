package Q9;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression. Type 'q' to exit.");

        while (true) {
            System.out.print("\nEnter an expression: ");
            String expression = scanner.nextLine();

            //exit if user types 'q'
            if (expression.equalsIgnoreCase("q")) {
                System.out.println("Exiting calculator");
                break;
            }

            boolean isValid = true;

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);
                //check if the character is a digit or an operator
                if (!Character.isDigit(ch) && "+-*/() ".indexOf(ch) == -1) {
                    isValid = false;
                    break;
                }
            }

            //if the expression is invalid, prompt the user to enter a valid expression
            if (!isValid) {
                System.out.println("Invalid expression. Please enter a valid expression.");
                continue;
            }

            try {
                System.out.println("Result: " + calculateExpression(expression));
            } catch (Exception e) {
                System.out.println("Invalid expression. Please enter a valid expression.");
            }
        }

        scanner.close();
    }

    //method to calculate the arithmetic expression
    public static int calculateExpression(String expression) {
        Stack<Integer> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        //iterate through the expression
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            //skip spaces
            if (ch == ' ') {
                continue;
            }

            if (ch == '(') {
                //push opening bracket to the operators stack
                operators.push(ch);
            } else if (ch == ')') {
                //evaluate the expression inside the brackets
                while (!operators.isEmpty() && operators.peek() != '(') {
                    evaluateTop(numbers, operators);
                }
                //remove the opening bracket
                operators.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                //evaluate operators with higher precedence
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    evaluateTop(numbers, operators);
                }
                operators.push(ch);
            } else {
                int num = 0;
                //handle multi-digit numbers
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                i--;
                numbers.push(num);
            }

        }

        //evaluate the remaining operators
        while (!operators.isEmpty()) {
            evaluateTop(numbers, operators);
        }

        return numbers.pop();
    }

    //method to evaluate the top of the stack
    public static void evaluateTop(Stack<Integer> numbers, Stack<Character> operators) {
        if (numbers.size() < 2 || operators.isEmpty()) {
            return;
        }

        int num2 = numbers.pop();
        int num1 = numbers.pop();
        char operator = operators.pop();

        //evaluate the expression and push the result to the numbers stack
        if (operator == '+') {
            numbers.push(num1 + num2);
        } else if (operator == '-') {
            numbers.push(num1 - num2);
        } else if (operator == '*') {
            numbers.push(num1 * num2);
        } else if (operator == '/') {
            numbers.push(num1 / num2);
        }
    }

    //method to determine the precedence of an operator
    public static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else {
            return 0;
        }
    }
}
