/**
 * Arithmetic calculator using the switch statement.
 * Returns the result of the operation after the = symbol
 */

import java.util.Scanner;
public class Calculator{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a number");
        float a = scanner.nextFloat();
        System.out.println("Please enter the operation");
        scanner.nextLine();
        String operation = scanner.nextLine();
        boolean done = false;
        float result = a;
        String lastOp="";
        float c=1;
        while (!operation.equals("=")) {
            System.out.println("Please enter a number");
            float b = scanner.nextFloat();

            switch (operation) {
                case "+":
                    result = result + b;
                    break;
                case "-":
                    result = result - b;
                    break;
                case "*":
                    if (lastOp.equals("+")) {
                        result = result-c+c* b;
                    }else if(lastOp.equals("-")){
                        result=result+c-(c*b);
                        }else{
                            result=result*b;
                            }
                    break;
                case "/":
                    if(lastOp.equals("+")){
                        result=result-c+(c/b);
                    }else if(lastOp.equals("-")){
                        result=result+c-(c/b);
                        }else {
                        result = result / b;
                    }
                    break;
                default:
                    System.out.println("Is not an operation");
            }

            c = b;
            lastOp=operation;
            System.out.println("Please enter the next operation");
            scanner.nextLine();
            operation = scanner.nextLine();

        }
        System.out.println("The result is: " + result);
    }
    }