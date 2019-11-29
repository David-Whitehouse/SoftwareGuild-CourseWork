/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.Dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sg.SuperSightings.Models.Location;
import sg.SuperSightings.Models.Power;
import sg.SuperSightings.Models.Supe;
import sg.SuperSightings.TestApplicationConfiguration;

/**
 *
 * @author ddubs
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Profile("daoTest")
public class SightingsDaoImplTest {
        @Autowired
        private JdbcTemplate template;
    
        @Autowired
        SightingsDao toTest;
    public SightingsDaoImplTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        String deleteLocations = "delete from Locations";
        template.update(deleteLocations);
        
                        String deleteSupes = "delete from Supes";
        template.update(deleteSupes);
        
        String deletePowers = "delete from Powers";
        template.update(deletePowers);
        

        
        String deleteSupeOrganization = "delete from SupeOrganization";
        template.update(deleteSupeOrganization);
        
        String deleteOrganizations = "delete from Organizations";
        template.update(deleteOrganizations);
        
        String deleteSightings = "delete from Sightings";
        template.update(deleteSightings);
        

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSupeById method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetSupeById() throws SupeDoesNotExistException, PowerDoesNotExistException {
        Supe supeToAdd = new Supe();
        supeToAdd.setName("SuperMan");
        supeToAdd.setDescription("SuperHuman Strength");
        Power powerToAdd = new Power();
        powerToAdd.setName("SuperHuman Strength");
        Power addedPower = toTest.addPower(powerToAdd);
        supeToAdd.setPower(addedPower);
        
        Supe addedSupe = toTest.addSupe(supeToAdd);
        
        Supe supeToAssert = toTest.getSupeById(addedSupe.getId());
      assertEquals(addedSupe.getId(), supeToAssert.getId());
      assertEquals("SuperMan", supeToAssert.getName());
      assertEquals("SuperHuman Strength", supeToAssert.getDescription());
      assertEquals("SuperHuman Strength", supeToAssert.getPower().getName());
    }
    
    @Test
    public void testGetSupeByBadId(){
            try {
                toTest.getSupeById(1000);
                fail();
            } catch (SupeDoesNotExistException ex) {
                
            }
    }

    /**
     * Test of getAllSupes method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllSupes() {
        Supe supeToAdd = new Supe();
        supeToAdd.setName("SuperMan");
        supeToAdd.setDescription("SuperHuman Strength");
        Power powerToAdd = new Power();
        powerToAdd.setName("SuperHuman Strength");
        Power addedPower = toTest.addPower(powerToAdd);
        supeToAdd.setPower(addedPower);
        
        toTest.addSupe(supeToAdd);
        
        Supe supeToAdd2 = new Supe();
        supeToAdd2.setName("AquaMan");
        supeToAdd2.setDescription("King of the Seven Seas");
        Power powerToAdd2 = new Power();
        powerToAdd2.setName("Commands Everything in the Sea");
        Power addedPower2 = toTest.addPower(powerToAdd2);
        supeToAdd2.setPower(addedPower2);
        
        toTest.addSupe(supeToAdd2);
        
        List<Supe> allSupes = toTest.getAllSupes();
        
        assertEquals(2, allSupes.size());
    }

    /**
     * Test of addSupe method, of class SightingsDaoImpl.
     */
    @Test
    public void testAddSupe() {
        Supe supeToAdd = new Supe();
        supeToAdd.setName("SuperMan");
        supeToAdd.setDescription("SuperHuman Strength");
        Power powerToAdd = new Power();
        powerToAdd.setName("SuperHuman Strength");
        Power addedPower = toTest.addPower(powerToAdd);
        supeToAdd.setPower(addedPower);
        
        toTest.addSupe(supeToAdd);
    }

    /**
     * Test of editSupe method, of class SightingsDaoImpl.
     */
    @Test
    public void testEditSupe() throws SupeDoesNotExistException {
        Supe supeToAdd = new Supe();
        supeToAdd.setName("SuperMan");
        supeToAdd.setDescription("SuperHuman Strength");
        Power powerToAdd = new Power();
        powerToAdd.setName("SuperHuman Strength");
        Power addedPower = toTest.addPower(powerToAdd);
        supeToAdd.setPower(addedPower);
        
        Supe added = toTest.addSupe(supeToAdd);
        
        Supe edited = toTest.getSupeById(added.getId());
        edited.setName("Batman");
        edited.setDescription("The Dark Knight");
        Power powerToAdd2 = new Power();
        powerToAdd2.setName("Rich");
        Power addedPower2 = toTest.addPower(powerToAdd2);
        edited.setPower(addedPower2);
        
        toTest.editSupe(edited);
        
        Supe toAssert = toTest.getSupeById(added.getId());
        assertEquals("Batman", toAssert.getName());
        assertEquals("The Dark Knight", toAssert.getDescription());
        assertEquals("Rich", toAssert.getPower().getName());
        
    }

    /**
     * Test of deleteSupeById method, of class SightingsDaoImpl.
     */
    @Test
    public void testDeleteSupeById() {
        Supe supeToAdd = new Supe();
        supeToAdd.setName("SuperMan");
        supeToAdd.setDescription("SuperHuman Strength");
        Power powerToAdd = new Power();
        powerToAdd.setName("SuperHuman Strength");
        Power addedPower = toTest.addPower(powerToAdd);
        supeToAdd.setPower(addedPower);
        
        Supe supeToDelete = toTest.addSupe(supeToAdd);
        
        Supe supeToAdd2 = new Supe();
        supeToAdd2.setName("AquaMan");
        supeToAdd2.setDescription("King of the Seven Seas");
        Power powerToAdd2 = new Power();
        powerToAdd2.setName("Commands Everything in the Sea");
        Power addedPower2 = toTest.addPower(powerToAdd2);
        supeToAdd2.setPower(addedPower2);
        
        toTest.addSupe(supeToAdd2);
        
        
        
        toTest.deleteSupeById(supeToDelete.getId());
        
        List<Supe> supes = toTest.getAllSupes();
        
        assertEquals(1, supes.size());
        
    }

    /**
     * Test of getLocationById method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetLocationById() throws Exception {
        Location locToAdd = new Location();
        
        locToAdd.setName("Empire State Building");
        locToAdd.setAddress("20 W 34th St, New York, NY 10001");
        locToAdd.setLatitude(new BigDecimal("40.748400"));
        locToAdd.setLongitude(new BigDecimal("73.985700"));
        
        Location addedLoc = toTest.addLoc(locToAdd);
        
        Location locToAssert = toTest.getLocationById(addedLoc.getId());
        
        assertEquals("Empire State Building", locToAssert.getName());
        assertEquals("20 W 34th St, New York, NY 10001", locToAssert.getAddress());
        assertEquals(new BigDecimal(40.748400), locToAssert.getLatitude());
        assertEquals(new BigDecimal(73.985700), locToAssert.getLongitude());
        
    }

    /**
     * Test of getAllLocations method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllLocations() {
        Location locToAdd = new Location();
        
        locToAdd.setName("Empire State Building");
        locToAdd.setAddress("20 W 34th St, New York, NY 10001");
        locToAdd.setLatitude(new BigDecimal("40.7484"));
        locToAdd.setLongitude(new BigDecimal("73.9857"));
        
        Location addedLoc = toTest.addLoc(locToAdd);
        
        assertEquals("Empire State Building", addedLoc.getName());
        assertEquals("20 W 34th St, New York, NY 10001", addedLoc.getAddress());
        assertEquals(new BigDecimal(40.7484), addedLoc.getLatitude());
        assertEquals(new BigDecimal(73.9857), addedLoc.getLongitude());
        
        List<Location> locs = toTest.getAllLocations();
        
        assertEquals(1, locs.size());
        
    }

    /**
     * Test of addLoc method, of class SightingsDaoImpl.
     */
    @Test
    public void testAddLoc() {
        Location locToAdd = new Location();
        
        locToAdd.setName("Empire State Building");
        locToAdd.setAddress("20 W 34th St, New York, NY 10001");
        locToAdd.setLatitude(new BigDecimal("40.7484"));
        locToAdd.setLongitude(new BigDecimal("73.9857"));
        
        Location addedLoc = toTest.addLoc(locToAdd);
        
        List<Location> locs = toTest.getAllLocations();
        
        assertEquals(1, locs.size());
    }

    /**
     * Test of editLoc method, of class SightingsDaoImpl.
     */
    @Test
    public void testEditLoc() {
    }

    /**
     * Test of deleteLocById method, of class SightingsDaoImpl.
     */
    @Test
    public void testDeleteLocById() {
        Location locToAdd = new Location();
        
        locToAdd.setName("Empire State Building");
        locToAdd.setAddress("20 W 34th St, New York, NY 10001");
        locToAdd.setLatitude(new BigDecimal("40.7484"));
        locToAdd.setLongitude(new BigDecimal("73.9857"));
        
        Location addedLoc = toTest.addLoc(locToAdd);
        
        toTest.deleteLocById(addedLoc.getId());
        List<Location> locs = toTest.getAllLocations();
        
        assertEquals(0, locs.size());
        
        
    }

    /**
     * Test of getPowerById method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetPowerById() throws Exception {
        
        
    }

    /**
     * Test of getAllPowers method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllPowers() {
    }

    /**
     * Test of addPower method, of class SightingsDaoImpl.
     */
    @Test
    public void testAddPower() {
        Power powerToAdd = new Power();
        powerToAdd.setName("SuperHuman Strength");
        
        
        
        Power powerToAssert = toTest.addPower(powerToAdd);
        
        assertEquals("SuperHuman Strength", powerToAssert.getName());
    }

    /**
     * Test of editPower method, of class SightingsDaoImpl.
     */
    @Test
    public void testEditPower() {
    }

    /**
     * Test of deletePowerById method, of class SightingsDaoImpl.
     */
    @Test
    public void testDeletePowerById() {
    }

    /**
     * Test of getOrgById method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetOrgById() throws Exception {
    }

    /**
     * Test of getAllOrgs method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllOrgs() {
    }

    /**
     * Test of addOrg method, of class SightingsDaoImpl.
     */
    @Test
    public void testAddOrg() {
    }

    /**
     * Test of editOrg method, of class SightingsDaoImpl.
     */
    @Test
    public void testEditOrg() {
    }

    /**
     * Test of deleteOrgById method, of class SightingsDaoImpl.
     */
    @Test
    public void testDeleteOrgById() {
    }

    /**
     * Test of getSightingById method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetSightingById() throws Exception {
    }

    /**
     * Test of getAllSightings method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetAllSightings() {
    }

    /**
     * Test of addSighting method, of class SightingsDaoImpl.
     */
    @Test
    public void testAddSighting() {
    }

    /**
     * Test of editSighting method, of class SightingsDaoImpl.
     */
    @Test
    public void testEditSighting() {
    }

    /**
     * Test of deleteSightingById method, of class SightingsDaoImpl.
     */
    @Test
    public void testDeleteSightingById() {
    }

    /**
     * Test of getSupesByLoc method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetSupesByLoc() {
    }

    /**
     * Test of getLocsBySupe method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetLocsBySupe() {
    }

    /**
     * Test of getSightingsByDate method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetSightingsByDate() {
    }

    /**
     * Test of getSupesByOrg method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetSupesByOrg() {
    }

    /**
     * Test of getOrgsBySupe method, of class SightingsDaoImpl.
     */
    @Test
    public void testGetOrgsBySupe() {
    }
    
}
