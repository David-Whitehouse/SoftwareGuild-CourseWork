/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.biggerbetteradd;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class BiggerBetterAdder {
    public static void main(String[] args) {
        int variable1;
        int variable2;
        int variable3;
       
        Scanner inputReader = new Scanner(System.in);
        
        System.out.println("What is the first number to add? ");
        variable1 = inputReader.nextInt();
        
        //variables added together and stored as variable4
        int variable4 = variable1 + variable2 + variable3;
        
        System.out.println(variable1 + variable2 + variable3);
        System.out.println(variable4);
    }
}