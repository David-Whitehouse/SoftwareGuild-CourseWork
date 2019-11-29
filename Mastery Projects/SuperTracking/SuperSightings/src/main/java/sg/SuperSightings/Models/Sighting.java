/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.Models;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ddubs
 */
public class Sighting {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Location location;
    private Supe supe;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the supe
     */
    public Supe getSupe() {
        return supe;
    }

    /**
     * @param supe the supe to set
     */
    public void setSupe(Supe supe) {
        this.supe = supe;
    }
    
}
