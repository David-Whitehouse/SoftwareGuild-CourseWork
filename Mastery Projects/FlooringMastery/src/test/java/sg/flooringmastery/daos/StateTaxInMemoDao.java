/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import sg.flooringmastery.dtos.State;

/**
 *
 * @author ddubs
 */
public class StateTaxInMemoDao implements StateTaxDao {
    private List<State> statesList = new ArrayList<>();
    
    public StateTaxInMemoDao (){
        State state1 = new State();
        state1.setStateName("MN");
        state1.setTaxRate(new BigDecimal ("6.25"));

        
            statesList.add(state1);
        
        State state2 = new State();
        state2.setStateName("WI");
        state2.setTaxRate(new BigDecimal ("5.5"));
        
        statesList.add(state2);
        
    }

    @Override
    public List<State> getAllStates() throws StateTaxDaoException {
        return statesList;
    }
}
