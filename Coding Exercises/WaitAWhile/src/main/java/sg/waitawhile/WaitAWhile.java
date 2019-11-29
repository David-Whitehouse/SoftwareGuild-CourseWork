/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.waitawhile;

/**
 *
 * @author ddubs
 */
public class WaitAWhile {
    
    public static void main(String[] args) {
    
        int timeNow = 5;
        int bedTime = 10;
        // if you change bedTime to 11 it runs for one more loop
        // if you change timeNow to 11 it doesn't run the loop and just prints the go to bed message
        
        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up just a liiiiiittle longer....");
            timeNow++; // Time passes
            // if timeNow++ is removed the loop runs infinitely
        }
        
        System.out.println("Oh. It's " + timeNow + " o'clock.");
        System.out.println("Guess I should go to bed ...");
    }
}
