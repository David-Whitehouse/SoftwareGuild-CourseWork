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
public class Triangle extends Shape{
    
    double side1;
    double side2;
    double side3;
    
    public Triangle (double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        
    }
    
    

    @Override
    public double getArea() {
        
        double p  = (side1 + side2 + side3)/2;
        
        double area = Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
        
        return area;
       
        
    }

    @Override
    public double getPerimeter() {
        
         double perimeter = side1 + side2 + side3;
         return perimeter;
        
            }
    
}
