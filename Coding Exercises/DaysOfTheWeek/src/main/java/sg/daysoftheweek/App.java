/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.daysoftheweek;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class App {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Please enter a day of the week: ");
        String input = scn.nextLine();

        DayOfTheWeek userInput = DayOfTheWeek.MONDAY;

        switch (input.toLowerCase()) {
            case "sunday":
                userInput = DayOfTheWeek.SUNDAY;
                break;
            case "monday":
                userInput = DayOfTheWeek.MONDAY;
                break;
            case "tuesday":
                userInput = DayOfTheWeek.TUESDAY;
                break;
            case "wednesday":
                userInput = DayOfTheWeek.WEDNESDAY;
                break;
            case "thursday":
                userInput = DayOfTheWeek.THURSDAY;
                break;
            case "friday":
                userInput = DayOfTheWeek.FRIDAY;
                break;
            case "saturday":
                userInput = DayOfTheWeek.SATURDAY;
                break;
        }    
    
        //use enum to calculate days of week till Friday
                switch (userInput) {
                    case SUNDAY:
                        System.out.println("5 days until Friday.");    
                        break;
                    case MONDAY:
                        System.out.println("4 days until Friday.");
                        break;
                    case TUESDAY:
                        System.out.println("3 days until Friday.");
                        break;
                    case WEDNESDAY:
                        System.out.println("2 days until Friday.");
                        break;
                    case THURSDAY:
                        System.out.println("1 day until Friday");
                        break;
                    case FRIDAY:
                        System.out.println("it is Friday!");
                        break;
                    case SATURDAY:
                        System.out.println("6 days until Friday.");
                        break;

                }

        }

    }
