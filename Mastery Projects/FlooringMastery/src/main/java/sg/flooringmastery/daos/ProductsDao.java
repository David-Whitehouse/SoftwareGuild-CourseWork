/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.util.List;
import sg.flooringmastery.dtos.Product;

/**
 *
 * @author ddubs
 */
public interface ProductsDao {
    
    public List<Product> getAllProducts()throws ProductsDaoException;
    
}
