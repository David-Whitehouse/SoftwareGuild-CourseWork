/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sg.flooringmastery.dtos.Product;

/**
 *
 * @author ddubs
 */
public class ProductsDaoFileImpl implements ProductsDao {
        String path;
        
    public ProductsDaoFileImpl(String path){
        this.path = path;
    }

    
    @Override
    public List<Product> getAllProducts() throws ProductsDaoException {
           List<Product> products = new ArrayList<>();
           
           FileReader reader = null;

        try {
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);
            scn.nextLine();

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");

                    Product toAdd = new Product();
                    toAdd.setProductType(cells[0]);
                    toAdd.setCostPerSquareFoot(new BigDecimal(cells[1]).setScale(2, RoundingMode.HALF_UP));
                    toAdd.setLaborCostPerSquareFoot(new BigDecimal(cells[2]).setScale(2, RoundingMode.HALF_UP));

                    products.add(toAdd);
                }
            }
            return products;

        } catch (FileNotFoundException ex) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new ProductsDaoException("Could not close reader for " + path, ex);
            }
        }
        return products;
            
           
    }
    
    
}
