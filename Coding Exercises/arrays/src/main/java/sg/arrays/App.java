/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.arrays;

import java.util.Random;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
        
 //       int [] userAges = {30, 40, 50};
        
        
        
       // System.out.println(userGraduated[0]);
        
  //      for( int i = 0; i < userAges.length; i++){
  //          System.out.println(userAges[i]);
  //      }
  //     
   //     for(int userAge : userAges )
   //         System.out.println(userAge);
   // }
//}

Random rng = new Random();

int [][] ticTacToeBoard = new int[3][];

for( int i = 0; i < ticTacToeBoard.length; i++) {
    ticTacToeBoard[0] = new int[rng.nextInt(10) + 1];
    
}
    }
static void print2d(int[][] toPrint){

        for(int i=0; i<toPrint.length; i++){
            for( int j = 0; j < toPrint[i].length)
                system.out.print (toPrint[i][j] + "\t");
            
                }
        System.out.println();
}

    }
}
