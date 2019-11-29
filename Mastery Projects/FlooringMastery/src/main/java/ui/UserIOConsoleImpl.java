/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author ddubs
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner scn = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.print(msg);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scn.nextLine();
    }
    
        @Override
    public String readStringNotBlank(String prompt) {
        String toReturn = "";
        
        boolean valid = false;
        while(!valid){
          print(prompt);
          toReturn = scn.nextLine();
          if(!toReturn.isBlank()){
              valid = true;
          }
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Double.parseDouble(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean valid = false;
        while (!valid) {
            toReturn = readDouble(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Float.parseFloat(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean valid = false;
        while (!valid) {
            toReturn = readFloat(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Integer.parseInt(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public int readIntOrEnterToContinue(String prompt) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            if ("".equals(userInput)) {
                valid = true;
                break;
            }
            try {
                toReturn = Integer.parseInt(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            toReturn = readInt(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public int readIntOrEnterToContinue(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            if ("".equals(userInput)) {
                valid = true;
                break;
            }
            toReturn = readInt(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            String userInput = readString(prompt);
            try {
                toReturn = Long.parseLong(userInput);
                valid = true;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Integer.MIN_VALUE;
        boolean valid = false;
        while (!valid) {
            toReturn = readLong(prompt);
            valid = toReturn >= min && toReturn <= max;
        }
        return toReturn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean valid = false;
        BigDecimal toReturn = new BigDecimal("0");
        
            try {
                while (!valid) {
                    String s = readString(prompt);
                    toReturn = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
                    valid = true;
                }
            } catch (NumberFormatException ex) {
            }
        return toReturn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        boolean valid = false;
        BigDecimal toReturn = new BigDecimal("0");
        while (!valid) {
            String s = readString(prompt);
            try {
                    toReturn = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
                    valid = toReturn.compareTo(min) < 0 && toReturn.compareTo(max) > 0;
            } catch (NumberFormatException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public BigDecimal readEditBigDecimal(String prompt) {
        boolean valid = false;
        BigDecimal toReturn = new BigDecimal("0");
        while (!valid) {
            String s = readString(prompt);

            if (s.equals("")) {
                valid = true;
                toReturn = null;
                break;
            }
            try {
                toReturn = new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
                valid = true;

            } catch (NumberFormatException ex) {

            }
        }
        return toReturn;
    }

// reads string from user and converts it into a local date to be used by service but also allows the user to press enter
    @Override
    public LocalDate readEditDate(String prompt) {
        boolean valid = false;
        LocalDate toReturn = LocalDate.now();

        while (!valid) {
            String userDate = readString(prompt);
            if (userDate.equals("")) {
                toReturn = null;
                valid = true;
                break;
            }
            try {
                toReturn = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                valid = true;
            } catch (DateTimeParseException ex) {

            }
        }
        return toReturn;
    }

// reads string from user and converts it into a local date to be used by service
    @Override
    public LocalDate readDate(String prompt) {
        boolean valid = false;
        LocalDate toReturn = LocalDate.now();

        while (!valid) {
            String userDate = readString(prompt);
            try {
                toReturn = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                valid = true;
            } catch (DateTimeParseException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        boolean valid = false;
        LocalDate toReturn = LocalDate.now();

        while (!valid) {
            String userDate = readString(prompt);
            try {
                toReturn = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                if (toReturn.isAfter(min) && toReturn.isBefore(max)) {
                    valid = true;
                }
            } catch (DateTimeParseException ex) {
            }
        }
        return toReturn;
    }

    @Override
    public boolean readYesOrNo(String prompt) {
        boolean commit = false;
        boolean valid = false;

        while (!valid) {
            String userChoice = readString(prompt);
            if (userChoice.equalsIgnoreCase("y")) {
                valid = true;
                commit = true;

            } else if (userChoice.equalsIgnoreCase("n")) {
                valid = true;
                commit = false;
            }
        }
        return commit;
    }

}
