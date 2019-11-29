/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.yourlifeinmovies;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
        String name;
        
        Scanner scn = new Scanner(System.in);
        
        System.out.println("Hey, let's play a game! What's your name?");
            name = scn.nextLine();
            
        System.out.println("Ok, " + name + " In what year were you born?");
            String yearBorn = scn.nextLine();
            int userYearBorn = Integer.parseInt(yearBorn);
            
        System.out.println("Well " + name + "...");
        
        if(userYearBorn < 2005) {
            
        System.out.println("Did you know that Pixar's 'Up' came out half a decade ago?");
        }
        
        if(userYearBorn < 1995) {
        System.out.println("And that the first Harry Potter came out over 15 years ago!");
        }
        
        if(userYearBorn < 1985) {
        System.out.println("Also, Space Jam came out not last decade, but the one before THAT.");
        }
        
        if(userYearBorn < 1975) {
        System.out.println("the original Jurassic Park release is closer to the date of the first lunar landing than it is to today.");
        }
        if(userYearBorn < 1965) {
        System.out.println("the MASH TV series has been around for almost half a century!");
        }
    }
}