/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.SupeService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.SuperSightings.Dao.LocationDoesNotExistException;
import sg.SuperSightings.Dao.OrgDoesNotExistException;
import sg.SuperSightings.Dao.PowerDoesNotExistException;
import sg.SuperSightings.Dao.SightingDoesNotExistException;
import sg.SuperSightings.Dao.SightingsDao;
import sg.SuperSightings.Dao.SupeDoesNotExistException;
import sg.SuperSightings.Models.Location;
import sg.SuperSightings.Models.Organization;
import sg.SuperSightings.Models.Power;
import sg.SuperSightings.Models.Sighting;
import sg.SuperSightings.Models.Supe;

/**
 *
 * @author ddubs
 */
@Component
public class SupeService {

    @Autowired
    SightingsDao dao;

    public List<Supe> getAllSupes() {
        return dao.getAllSupes();
    }

    public List<Power> getAllPowers() {
        return dao.getAllPowers();
    }

    public void addSupe(Supe toAdd) {
        try {
            Power powerToAdd = dao.getPowerById(toAdd.getPower().getId());
            toAdd.setPower(powerToAdd);

            dao.addSupe(toAdd);
        } catch (PowerDoesNotExistException ex) {
            toAdd = null;
        }
    }

    public Power getPowerById(int id) {
        try {
            return dao.getPowerById(id);
        } catch (PowerDoesNotExistException ex) {
            return null;
        }
    }

    public void addPower(Power toAdd) {
        dao.addPower(toAdd);
    }

    public Supe getSupeById(Integer id) {
        try {
            return dao.getSupeById(id);
        } catch (SupeDoesNotExistException ex) {
            return null;
        }
    }

    public void editSupe(Supe edited) {
        dao.editSupe(edited);
    }

    public void deleteSuperById(Integer id) {
        dao.deleteSupeById(id);
    }

    public void editPower(Power edited) {
        dao.editPower(edited);
    }

    public void deletePowerById(Integer powerId) {
        dao.deletePowerById(powerId);
    }

    public List<Organization> getAllOrgs() {
        return dao.getAllOrgs();
    }

    public void addOrg(Organization toAdd) {
        dao.addOrg(toAdd);

    }

    public Organization getOrgById(int orgId) {

        try {
            return dao.getOrgById(orgId);
        } catch (OrgDoesNotExistException ex) {
            return null;
        }
    }

    public void addSupe(Supe toAdd, List<Organization> orgs) {
        try {
            Power powerToAdd = dao.getPowerById(toAdd.getPower().getId());
            toAdd.setPower(powerToAdd);

            Supe supeForId = dao.addSupe(toAdd);
            int supeId = supeForId.getId();

            addSupeOrgs(supeId, orgs);

        } catch (PowerDoesNotExistException ex) {
            toAdd = null;
        }
    }

    public void removeOrgsforSupe(Integer id) {
        dao.deleteOrgsForSupe(id);
    }

    public void addSupeOrgs(Integer id, List<Organization> orgs) {
        for (Organization org : orgs) {
            int orgId = org.getId();
            dao.addSupeOrgs(id, orgId);
        }

    }

    public void deleteOrgById(Integer orgId) {
        dao.deleteOrgById(orgId);
    }

    public void editOrg(Organization edited) {
        dao.editOrg(edited);
    }

    public List<Location> getAllLocs() {
        return dao.getAllLocations();
    }

    public Location getLocById(int id) {
        try {
            return dao.getLocationById(id);
        } catch (LocationDoesNotExistException ex) {
            return null;
        }
    }

    public void addLoc(Location toAdd) {
        dao.addLoc(toAdd);
    }

    public void editLocation(Location edited) {
        dao.editLoc(edited);
    }

    public void deleteLocById(Integer locId) {
        dao.deleteLocById(locId);
    }

    public List<Sighting> getAllSights() {
        return dao.getAllSightings();
    }

    public void addSighting(Sighting toAdd) {
        dao.addSighting(toAdd);
    }
    
//    public List<Sighting> constructSights(List<Sighting> sights){
//        List<Sighting> Sightings = new ArrayList();
//        for(Sighting sight : sights){
//            sight.getLocation().getId();
//        }
//        
//    }

    public Sighting getSightingById(int id) {
        try {
            return dao.getSightingById(id);
        } catch (SightingDoesNotExistException ex) {
            return null;
        }
    }

    public void editSighting(Sighting edited) {
        
        
        dao.editSighting(edited);
    }

    public void deleteSightingById(int sightId) {
        dao.deleteSightingById(sightId);
    }
}
