/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.morebucketsmorefun;

/**
 *
 * @author ddubs
 */
public class MoreBucketsMoreFun {
    public static void main(String[] args) {
        
        //Declare ALL THE THINGS
        //(Usually it's a good idea to declare them at the beginning of the program)
        int butterflies, beetles, bugs;
        String color, size, shape, thing;
        double number;
        
        //Now give a couple of them some values
        butterflies = 2;
        beetles = 4;
        
        bugs = butterflies + beetles;
        System.out.println("There are only" + butterflies + " butterflies,");
        System.out.println("but " + bugs + " bugs total.");
        
        System.out.println("Uh oh, my dog ate one.");
        //we are using the -- operator to remove one count from the integer for butterflies
        butterflies--;
        System.out.println("Now there are only " + butterflies + " butterflies left.");
        System.out.println("But still " + bugs + " bugs left, wait a minute!!!");
        System.out.println("Maybe I just counted wrong the first time...");
        //number of bugs does not change because the assignment of bugs occurs before we remove one butterfly from the equation
    }
}
