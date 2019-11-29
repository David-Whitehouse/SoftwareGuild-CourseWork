/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
        
        int userWins = 0;
        int compWins = 0;
        int ties = 0;
        int roundNumber = 1;
        int restart = 0;

        Scanner scn = new Scanner(System.in);

        System.out.println("___________________________________________");
        System.out.println("          1. Start new game");
        System.out.println("          2. Quit");
        System.out.println("___________________________________________");

        System.out.print("Please choose a menu option from above:");
        int menuChoice = scn.nextInt();

        while (restart != 2){
        if (menuChoice != 2) {
            System.out.println("How many rounds would you like to play (1-10): ");
            int roundChoice = scn.nextInt();

            if (roundChoice > 10 || roundChoice < 1) {
                System.out.println("Error: must choose a number of rounds between 1-10 ");
                roundChoice = scn.nextInt();
            } else {

                while (roundNumber <= roundChoice) {
                    System.out.print("Please choose the numerical value for, rock(1) paper(2) or Scissors(3):");
                    int userHand = scn.nextInt();
                   
                    if (userHand < 1 || userHand > 3) {
                        System.out.print("Error: must choose 1, 2, or 3 for rock, paper, or scissors respectively:");
                        userHand = scn.nextInt();
                    }
                    Random rGen = new Random();
                    int compHand = compHand();
                    
                    compChose(compHand);
                    
                    
                    if (userHand == compHand){
                        System.out.println("round is a tie!");
                        ties+=1;                         
                    }else if(userHand == 1){ 
                                switch(compHand){
                                    case 2:
                                        System.out.println("computer wins this round!");
                                        compWins += 1;
                                        break;
                                    case 3:
                                        System.out.println("you win this round!");
                                        userWins += 1;
                                        break;
                                }
                    }else if(userHand == 2){ 
                                switch(compHand){
                                    case 1:
                                        System.out.println("you win this round!");
                                        userWins += 1;
                                        break;
                                    case 3:
                                        System.out.println("Computer wins this round!");
                                        compWins += 1;
                                        break;
                                }
                    }else if (userHand == 3){ 
                                switch(compHand){
                                    case 1:
                                        System.out.println("computer wins this round!");
                                        compWins += 1;
                                        break;
                                    case 2:
                                        System.out.println("you win this round!");
                                        userWins += 1;
                                        break;
                                }
                    }
                                roundNumber++;
                }
                System.out.println("");
                System.out.print("You won: " + userWins + " rounds      ");
                System.out.print("Computer won: " + compWins + " rounds     ");
                System.out.println("Ties: " + ties);
               
            if (userWins > compWins){
                System.out.println("You win the game!");
            }else if (userWins < compWins) {
                    System.out.println("Computer wins the game!");
            } else if(userWins == compWins)
                 System.out.println("Game is a tie!");
            }
          
            }
            System.out.println("");
            System.out.println("Would you like to play again? select '1' for yes and '2' for no.");
            restart = scn.nextInt();
        }
        
        
    }
    
    public static int compHand(){
            Random rGen = new Random();
            int x = rGen.nextInt(3) + 1;
            return x;
        
    }
    
    public static void compChose(int compChoice){
          switch(compChoice){
              case 1:
                  System.out.println("Computer chose rock.");
                break;
              case 2:
                  System.out.println("Computer chose paper.");
                  break;
              case 3:
                  System.out.println("Computer chose scissors.");
                  break;
          }
    }

}
//    public static void gameLogic(int userHand, int compHand, int ties, int userWins, int compWins){
//            
//        if (userHand == compHand){
//                        ties+=1;                         
//                    }else if{
//            }

    

         
    

  

            
         
            

   

