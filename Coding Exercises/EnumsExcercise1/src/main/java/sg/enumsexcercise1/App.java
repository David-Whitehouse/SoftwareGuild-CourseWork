/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.enumsexcercise1;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
        

    Scanner scn = new Scanner(System.in);
    
    DayOfWeek weekDay = DayOfWeek.Friday;
    
    
    String userInput; 
        
        boolean validInput = false;
        while( !validInput){
            System.out.print("Please enter the day of the week: ");
            userInput = scn.nextLine().toLowerCase();
            validInput = true;
            switch (userInput) {
                case "monday":
                    weekDay = DayOfWeek.Monday;
                    break;
                case "tuesday":
                    weekDay = DayOfWeek.Tuesday;
                    break;
                case "wednesday":
                    weekDay = DayOfWeek.Wednesday;
                    break;
                case "thursday":
                    weekDay = DayOfWeek.Thursday;
                    break;
                case "friday":
                    weekDay = DayOfWeek.Friday;
                    break;
                case "saturday":
                    weekDay = DayOfWeek.Saturday;
                    break;
                case "sunday":
                    weekDay = DayOfWeek.Sunday;
                    break;
                default:
                    validInput = false;
                    
            }
        }
            int howManyDays = -1;
            
            switch (weekDay){
                case Monday: howManyDays = 4; break;
                case Tuesday: howManyDays = 3; break;
                case Wednesday: howManyDays = 2; break;
                case Thursday: howManyDays = 1; break;
                case Friday: howManyDays = 0; break;
                case Saturday: howManyDays = 6; break;
                case Sunday: howManyDays = 5; break;
                
                    
        }
    
        System.out.println("There are "+howManyDays+" days until Friday.");
    
    
    }  
}
