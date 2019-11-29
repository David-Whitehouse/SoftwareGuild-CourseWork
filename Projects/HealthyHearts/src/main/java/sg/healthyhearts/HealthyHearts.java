/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class HealthyHearts {
    public static void main(String[] args) {
                
        Scanner scn = new Scanner(System.in);

        
        System.out.println("What is your Age?   ");
        int userAge = scn.nextInt();
        
        int heartRateMax = (220 - userAge);
        int heartZoneMin = (heartRateMax/2);
        int heartZoneMax = (int)(heartRateMax*.85);
       
        System.out.println("Your Maximum heart rate should be " + heartRateMax + " beats per minute");
        System.out.println("Your target HR Zone is " + heartZoneMin + " - " + heartZoneMax + " beats per minute");
    }
}
