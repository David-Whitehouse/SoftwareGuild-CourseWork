/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.staypositive;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class StayPositive {
    public static void main(String[] args) {
        
        Scanner scn = new Scanner(System.in);
        String number;
        int userNumber = -1;
        
        System.out.println("What number should I count down from? ");
        userNumber = scn.nextInt();
        
        System.out.println("");
        System.out.println("Here goes!");
        
        while(userNumber >= 0) {
            
            
            
            System.out.print(userNumber + " ");
            userNumber--;
            
            if(userNumber % 10 == 0){
            System.out.println();
            }
    }
        System.out.println("");
        System.out.println("Whew, better stop there...!");
        
    }
 
}
