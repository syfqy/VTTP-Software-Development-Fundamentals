package myapp;
import java.io.Console;

public class Calculator {
    public static void main(String[] args) {
        Console cons = System.console();
        String op1 = cons.readLine("Enter the first number: ").trim();
        String op2 = cons.readLine("Enter the second number: ").trim();
        String operator = cons.readLine("Enter the operator: ");

        int val0 = Integer.parseInt(op1);
        int val1 = Integer.parseInt(op2);

        float result = 0f;

        switch(operator) {
            case "add":
                result = (float)(val0+val1);
                break;
            case "multiply":
                result = (float)(val0*val1);
                break;
            case "subtract":
                result = (float)(val0-val1);
                break;
            case "divide":
                // if (val1 == 0) {
                //     System.out.println("Cannot divide by 0");
                //     break;
                // }
                result = (float)(val0/val1);
                break;
        }

        System.out.printf("Result: %d %s %d = %.3f", val0, operator, val1, result);        

    }
}