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
public class Circle extends Shape{

    double radius; 
    
    public Circle(double radius){
        
          this.radius = radius;
    }
    
    @Override
    public double getArea() {
        double area = Math.PI * radius * radius;
        
       return area;
       
    }

    @Override
    public double getPerimeter() {
        
        double perimeter = Math.PI * 2 * radius;
        return perimeter;
        
    }
    
}
