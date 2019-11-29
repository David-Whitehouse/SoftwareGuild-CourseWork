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
import sg.flooringmastery.dtos.State;

/**
 *
 * @author ddubs
 */
public class StateTaxDaoFileImpl implements StateTaxDao {

    String path;

    public StateTaxDaoFileImpl(String path) {
        this.path = path;
    }

    @Override
    public List<State> getAllStates() throws StateTaxDaoException {
        List<State> states = new ArrayList<>();

        FileReader reader = null;

        try {
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);
            scn.nextLine();

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");

                    State toAdd = new State();
                    toAdd.setStateName(cells[0]);
                    toAdd.setTaxRate(new BigDecimal(cells[1]).setScale(2, RoundingMode.HALF_UP));

                    states.add(toAdd);
                }
            }
            return states;

        } catch (FileNotFoundException ex) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new StateTaxDaoException("Could not close reader for " + path, ex);
            }
        }
        return states;
    }

//    @Override
//    public BigDecimal getTaxRateByState(String stateAbbreviation) throws StateTaxDaoException{
//        BigDecimal taxRate = new BigDecimal("0");
//        State orderState = new State();
//
//        List<State> states = getAllStates();
//        for (State toCheck : states) {
//            if (toCheck.getStateName().equals(stateAbbreviation)) {
//                toCheck = orderState;
//                break;
//            }
//        }
//        taxRate = orderState.getTaxRate();
//
//        return taxRate;
//    }
}
