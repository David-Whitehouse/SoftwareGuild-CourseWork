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
public class Rectangle extends Shape{
    
    double length;
    double width;
   

    
 
    
    
    public Rectangle(String color,double length, double width){
        
          super(color, "Rectangle");
          this.length = length;
          this.width = width;
          
         
    }

    @Override
    public double getArea() {
        double area = length * width;
        return area;
    }

    @Override
    public double getPerimeter() {
        double perimeter = (2 * length) + (2 * width);
        return perimeter;
        
    }
    }
    
    

   
  
    
  

