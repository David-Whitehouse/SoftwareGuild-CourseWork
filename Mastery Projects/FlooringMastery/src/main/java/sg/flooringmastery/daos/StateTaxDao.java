/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.math.BigDecimal;
import java.util.List;
import sg.flooringmastery.dtos.State;

/**
 *
 * @author ddubs
 */
public interface StateTaxDao {

    public List<State> getAllStates() throws StateTaxDaoException;
    
//    public BigDecimal getTaxRateByState(String StateAbbreviation) throws StateTaxDaoException;
    
}
    