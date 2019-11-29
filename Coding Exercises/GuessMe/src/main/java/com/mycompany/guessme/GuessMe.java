/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessme;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class GuessMe {
    public static void main(String[] args) {
        
        int number = 11;
        Scanner scn = new Scanner(System.in);
        int guess;
                
        System.out.println("I've chosen a number. Betcha can't guess it!");
        guess = scn.nextInt();
        System.out.println("Your guess: " + guess);
        
        if (guess == number){
        
        System.out.println("Wow!, nice guess! That was it!");
    
    } else if (guess < number){
    
            System.out.println("Ha, nice try - too low! I chose " + number);

    } else if (guess > number){
    
            System.out.println("Too bad, way too high. I chose " + number);        
            }
    }
}
