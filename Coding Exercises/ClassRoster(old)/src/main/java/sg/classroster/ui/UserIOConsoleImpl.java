/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;
/**
 *
 * @author dsmelser
 */
public class UserIOConsoleImpl implements UserIO {
    Scanner scn = new Scanner( System.in );
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
    
        @Override
    public String readString(String prompt) {
        print( prompt );
        return scn.nextLine();
    }
    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while( !valid ){
            String userInput = readString( prompt );
            try{
                toReturn = Double.parseDouble(  userInput );
                valid = true;
            } catch ( NumberFormatException ex ){}
        }
        return toReturn;
    }
    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while( !valid ){
            toReturn = readDouble( prompt );
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }
    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while( !valid ){
            String userInput = readString( prompt );
            try{
                toReturn = Float.parseFloat(  userInput );
                valid = true;
            } catch ( NumberFormatException ex ){}
        }
        return toReturn;
    }
    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while( !valid ){
            toReturn = readFloat( prompt );
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }
    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while( !valid ){
            String userInput = readString( prompt );
            try{
                toReturn = Integer.parseInt(userInput );
                valid = true;
            } catch ( NumberFormatException ex ){}
        }
        return toReturn;   
    }
    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while( !valid ){
            toReturn = readInt( prompt );
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }
    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;
        boolean valid = false;
        while( !valid ){
            String userInput = readString( prompt );
            try{
                toReturn = Long.parseLong(userInput );
                valid = true;
            } catch ( NumberFormatException ex ){}
        }
        return toReturn;      }
    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while( !valid ){
            toReturn = readLong( prompt );
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;    
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean valid = false;
        BigDecimal toReturn = null;
        while(!valid){
            String s = readString(prompt);
            try{
                
            toReturn = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
            valid = true;
            
            }catch(UnsupportedOperationException ex){}
        }    
            return toReturn;
    }
}