/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.birthstones;

import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class BirthStones {
    public static void main(String[] args) {
        
        String birthStone;
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("What month's birthstone are you wanting to know? (1-12)");
            String birthMonth   = userInput.nextLine();
            int userBirthMonth = Integer.parseInt(birthMonth);
    
             switch (userBirthMonth) {
                 case 1:
                     birthStone = "January's birthstone is Garnet";
                     break;
                 case 2:
                     birthStone = " February's birthstone is Amethyst";
                     break;
                 case 3:
                     birthStone = "March's birthstone is Aquamarine";
                     break;
                 case 4:
                     birthStone = "April's birthstone is Diamond";
                     break;
                 case 5:
                     birthStone = "May's birthstone is Emerald";
                     break;
                 case 6:
                     birthStone = "June's birthstone is Pearl";
                     break;
                 case 7:
                     birthStone = "July's birthstone is Ruby";
                     break;
                 case 8:
                     birthStone = "August's birthstone is Peridot";
                     break;
                 case 9:
                     birthStone = "September's birthstone is Sapphire";
                     break;
                 case 10:
                     birthStone = "October's birthstone is Opal";
                     break;
                 case 11:
                     birthStone = "November's birthstone is Topaz";
                     break;
                 case 12:
                     birthStone = "December's birthstone is Turquoise";
                     break;
                 default:
                     birthStone = "I think you must be confused, " + birthMonth + " doesn't match a month.";
                          
             }
                System.out.println(birthStone);
    }
}
