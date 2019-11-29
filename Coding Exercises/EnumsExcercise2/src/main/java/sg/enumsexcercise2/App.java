/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.enumsexcercise2;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
       
       IntMath calculator = new IntMath();
       
       int op1 = getOperand(1);
       MathOperator op = getOperator();
       int op2 = getOperand(2);
       
       int answer = calculator.calculate(op, op1, op2);
       
        System.out.println("The answer is: " + answer);
       }
   
private static int getOperand(int n){
    int operand = Integer.MIN_VALUE;
    Scanner scn = new Scanner(System.in);
    boolean valid = false;
       while( !valid) {
           try{
               System.out.println("Please enter operand "+n+": ");
               String userInput = scn.nextLine();
               n = Integer.parseInt(userInput);
               valid = true;
           } catch (NumberFormatException ex){}
          
       }
        return n;
}

private static MathOperator getOperator(){
    MathOperator op = MathOperator.DIVIDE;
    boolean valid = false;
    Scanner scn = new Scanner(System.in);
       while( !valid ) {
           System.out.println("Please enter operator (+,-,*,/):");
              String userInput = scn.nextLine();
              valid = true;
              switch(userInput) {
                  case "+": op = MathOperator.PLUS;break;
                  case "-": op = MathOperator.MINUS; break;
                  case "*": op = MathOperator.MULTIPLY; break;
                  case "/": op = MathOperator.DIVIDE; break;
                  default: valid = false; break;
              }
       }
                return op;
}
}

