/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shapes;

/**
 *
 * @author Chad
 */
public class App {
    
    public static void main(String[] args) {
        
       Rectangle r = new Rectangle(2.0, 5.0);
       Square s = new Square(4.0);
       Circle c = new Circle(3.0);
       Triangle t = new Triangle(3.0, 4.0, 5.0);
       
        
        Shape[] shapesArray = {r, s, c, t};
        
       for(int i = 0; i<shapesArray.length; i++){
           
           System.out.println(shapesArray[i].getArea());
           System.out.println(shapesArray[i].getPerimeter());        
                  
           
       }
        
        
        
        
        
        
        
        
        
//        double area = r.getArea();
//        
//        System.out.println(area);
//        
//        double perim = r.getPerimeter();
//                
//        System.out.println(perim);      
        
        
        
        
        
       
        
    }
    
}
