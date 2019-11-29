/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.stocks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ddubs
 */
public class App {

    public static void main(String[] args) {
        String path = "C:\\Users\\ddubs\\Desktop\\HoneywellStock.csv";
        

        List<DailyQuote> stockData = new ArrayList<>();

        try {
            Reader reader = new FileReader(path);
            Scanner scn = new Scanner(reader);
            scn.nextLine();

            while (scn.hasNextLine()) {

                String line = scn.nextLine();
                line = line.replaceAll("\"", "");
                String[] cells = line.split(",");
                String[] date = cells[0].split("/");

                    DailyQuote toAdd = new DailyQuote();

                    toAdd.setYear(Integer.parseInt(date[0]));
                    toAdd.setMonth(Integer.parseInt(date[1]));
                    toAdd.setDay(Integer.parseInt(date[2]));
                    toAdd.setClose(Double.parseDouble(cells[1]));
                    toAdd.setVolume((int) Double.parseDouble(cells[2]));
                    toAdd.setOpen(Double.parseDouble(cells[3]));
                    toAdd.setHigh(Double.parseDouble(cells[4]));
                    toAdd.setLow(Double.parseDouble(cells[5]));

                    stockData.add(toAdd);

            }    

                double sum = 0;

                for (DailyQuote d : stockData) {
                    sum += d.getClose();
                    
                }
                

                double average = sum / stockData.size();
                System.out.println(average);
            
                
                int daysAboveAverage = 0;
                for (DailyQuote d : stockData){
                    if( d.getClose() > average )
                        ++daysAboveAverage;
                }
                
                System.out.println(daysAboveAverage);
             
                
                
        }
            catch (FileNotFoundException ex) {
            System.out.println("Error: Could not locate file C:\\Users\\ddubs\\Desktop\\HoneywellStock.csv ");
            }           
        }
    }
