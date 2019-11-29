/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.Models;

/**
 *
 * @author ddubs
 */
public class Supe {
    private int id;
    private String name;
    private String description;
    private Power power;

    /**
     * @return the supeId
     */
    public int getId() {
        return id;
    }

    /**
     * @param supeId the supeId to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the power
     */
    public Power getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(Power power) {
        this.power = power;
    }
    
}
