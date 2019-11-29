/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.spacerustlers;

/**
 *
 * @author ddubs
 */
public class SpaceRustlers {
    public static void main(String[] args) {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;
        
        if(aliens > spaceships){
            System.out.println("Vrroom, vroom! Let's get going!");
        // the below else statement executes a separate code when the above if statement returns a false response
        } else{
            System.out.println("There aren't enough green guys to drive these ships!");
        }
        
        if(cows == spaceships){
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");
        // the else if statement below allows for another if statmenet to occur when the above if statement returns as false
        } else if (cows > spaceships){
            System.out.println("Dangit! I don't know how we're going to fit all these cows in here!");
        } else{
            System.out.println("Too many ships! Not enough cows.");
        }
        
        if(aliens > cows){
            System.out.println("Hurrah, we've got the grub! Hamburger party on Alpha Centauri!");
        } else {
            System.out.println("Oh no! The herds got restless and took over! Looks like _we're_ hamburger now!!");
        }
    }
}
