/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import sg.flooringmastery.dtos.Product;


/**
 *
 * @author ddubs
 */
public class ProductsInMemoDao implements ProductsDao {
    private List<Product> productsList = new ArrayList<>();
    
    public ProductsInMemoDao (){
        Product product1 = new Product();
        product1.setProductType("Wood");
        product1.setCostPerSquareFoot(new BigDecimal ("3.50"));
        product1.setLaborCostPerSquareFoot(new BigDecimal ("4.00"));
        
        productsList.add(product1);
        
        Product product2 = new Product();
        product2.setProductType("Vinyl");
        product2.setCostPerSquareFoot(new BigDecimal ("2.50"));
        product2.setLaborCostPerSquareFoot(new BigDecimal ("3.50"));
        
        productsList.add(product2);
        
    }

    @Override
    public List<Product> getAllProducts() throws ProductsDaoException {
        return productsList;
    }
    
    
}
