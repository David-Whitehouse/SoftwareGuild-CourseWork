/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.Dao;

import java.time.LocalDate;
import java.util.List;
import sg.SuperSightings.Models.Location;
import sg.SuperSightings.Models.Organization;
import sg.SuperSightings.Models.Power;
import sg.SuperSightings.Models.Sighting;
import sg.SuperSightings.Models.Supe;

/**
 *
 * @author ddubs
 */
public interface SightingsDao {
    Supe getSupeById(int supeId) throws SupeDoesNotExistException;
    
    List<Supe> getAllSupes();
    
    Supe addSupe(Supe supeToAdd);
    
    void editSupe(Supe editedSupe);

    void deleteSupeById(int id);
    
    Location getLocationById(int locId)throws LocationDoesNotExistException;
    
    List<Location> getAllLocations();
    
    Location addLoc (Location locToAdd);
    
    void editLoc (Location editedLoc);
    
    void deleteLocById (int locId);
    
    Power getPowerById(int powerId)throws PowerDoesNotExistException;
    
    List<Power> getAllPowers();
    
    Power addPower(Power powerToAdd);
    
    void editPower(Power editedPower);
    
    void deletePowerById(int powerId);
    
    Organization getOrgById(int orgId) throws OrgDoesNotExistException;
    
    List<Organization> getAllOrgs();
    
    Organization addOrg(Organization orgToAdd);
    
    void editOrg (Organization editedOrg);
    
    void deleteOrgById(int orgId);
    
    Sighting getSightingById (int sightId) throws SightingDoesNotExistException;
    
    List<Sighting> getAllSightings();
    
    Sighting addSighting(Sighting sightingToAdd);
    
    void editSighting(Sighting editedSighting);
    
    void deleteSightingById(int sightId);
    
    List<Supe> getSupesByLoc(int locId);
    
    List<Location> getLocsBySupe(int supeId);
    
    List<Sighting> getSightingsByDate(LocalDate date);
    
    List<Supe> getSupesByOrg(int orgId);
    
    List<Organization> getOrgsBySupe(int supeId);

    public void addSupeOrgs(int supeId, int orgId);

    public void deleteOrgsForSupe(Integer id);
    
    
    
    
    
    
    
    
    
    
    
    
}
