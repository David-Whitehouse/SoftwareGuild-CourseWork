/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.menuofchampions;

/**
 *
 * @author ddubs
 */
public class MenuOfChampions {
    public static void main(String[] args) {
        
        String restName = "NIGHT VALE";
        String pizza = "Slice of Big Rico Pizza";
        String pie = "Invisible Strawberry Pie";
        String omelet = "Denver Omelet";
        double pizzaPrice = 500.00;
        double piePrice = 2.00;
        double omeletPrice = 1.50;        
        
        
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println("");
        System.out.println("                WELCOME TO RESTAURANT " + restName + "!");
        System.out.println("                        Today's Menu Is...");
        System.out.println("");
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        
        
        
        System.out.println("");
        System.out.println("            " + pizza + "   $" + String.format ("%.2f", pizzaPrice));
        System.out.println("            " + pie + "    $" + String.format ("%.2f", piePrice));
        System.out.println("            " + omelet + "               $" + String.format ("%.2f", omeletPrice));
    }
  
}
