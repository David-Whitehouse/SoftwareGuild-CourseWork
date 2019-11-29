/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
public class SightingsDaoImpl implements SightingsDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Supe getSupeById(int supeId) throws SupeDoesNotExistException {
        try {
            String query = "Select Supes.Id as SupeId, Supes.`Name` as SupeName, Description, \n"
                    + "Powers.Id as PowerId, Powers.`Name` as PowerName\n"
                    + "from supes\n"
                    + "inner join powers on supes.powerId = powers.Id\n"
                    + "where Supes.Id = ?;";
            return jdbc.queryForObject(query, new SupeMapper(), supeId);
        } catch (DataAccessException ex) {
            throw new SupeDoesNotExistException("Supe with ID: " + supeId + " does not exist.");
        }
    }

    @Override
    public List<Supe> getAllSupes() {
        String query = "Select Supes.Id as SupeId, Supes.`Name` as SupeName, Description, \n"
                + "Powers.Id as PowerId, Powers.`Name` as PowerName\n"
                + "from supes\n"
                + "inner join powers on supes.powerId = powers.Id";

        return jdbc.query(query, new SupeMapper());
    }

    @Override
    @Transactional
    public Supe addSupe(Supe supeToAdd) {
        String query = "Insert into Supes(`Name`,`Description`, PowerId) Values (?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                toReturn.setString(1, supeToAdd.getName());
                toReturn.setString(2, supeToAdd.getDescription());
                toReturn.setInt(3, supeToAdd.getPower().getId());

                return toReturn;
            }
        };

        jdbc.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        supeToAdd.setId(generatedId);

        return supeToAdd;
    }

    @Override
    public void editSupe(Supe editedSupe) {
        String query = "Update Supes set `Name` = ?, `Description` = ?, PowerId = ?\n"
                + "where id = ?;";

        jdbc.update(query, editedSupe.getName(), editedSupe.getDescription(), editedSupe.getPower().getId(), editedSupe.getId());
    }

    @Override
    public void deleteSupeById(int id) {
        String deleteSighting = "Delete From Sightings\n"
                + "Where SupeId = ?";

        jdbc.update(deleteSighting, id);

        String deleteSupeOrg = "Delete From Supeorganization\n"
                + "Where SupeId = ?";

        jdbc.update(deleteSupeOrg, id);

        String deleteSupe = "Delete From Supes\n"
                + "Where Id = ?";

        jdbc.update(deleteSupe, id);

    }

    @Override
    public Location getLocationById(int locId) throws LocationDoesNotExistException {
        String query = "Select * from Locations Where Id = ?";
        try {

            return jdbc.queryForObject(query, new LocationMapper(), locId);
        } catch (DataAccessException ex) {
            throw new LocationDoesNotExistException("Location with ID:" + locId + " does not exist.");
        }
    }

    @Override
    public List<Location> getAllLocations() {
        String query = "Select * from Locations";

        return jdbc.query(query, new LocationMapper());
    }

    @Override
    public Location addLoc(Location locToAdd) {
        String query = "Insert into Locations(`Name`, Address, Latitude, Longitude) Values (?,?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                toReturn.setString(1, locToAdd.getName());
                toReturn.setString(2, locToAdd.getAddress());
                toReturn.setBigDecimal(3, locToAdd.getLatitude());
                toReturn.setBigDecimal(4, locToAdd.getLongitude());

                return toReturn;
            }
        };

        jdbc.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        locToAdd.setId(generatedId);

        return locToAdd;
    }

    @Override
    public void editLoc(Location editedLoc) {
        String query = "Update Locations set `Name` = ?, Address = ?, Latitude = ?, Longitude = ?\n"
                + "where id = ?;";

        jdbc.update(query, editedLoc.getName(), editedLoc.getAddress(),
                editedLoc.getLatitude(), editedLoc.getLongitude(), editedLoc.getId());
    }

    @Override
    public void deleteLocById(int locId) {
        String deleteSighting = "delete from Sightings where LocationId = ?";

        jdbc.update(deleteSighting, locId);

        String deleteLoc = "delete from Locations where Id = ?";

        jdbc.update(deleteLoc, locId);
    }

    @Override
    public Power getPowerById(int powerId) throws PowerDoesNotExistException {
        try {
            String query = "Select * from Powers where Id = ?";

            return jdbc.queryForObject(query, new PowerMapper(), powerId);
        } catch (DataAccessException ex) {
            throw new PowerDoesNotExistException("Power with ID: " + powerId + " does not exist.");
        }
    }

    @Override
    public List<Power> getAllPowers() {
        String query = "Select * from Powers";

        return jdbc.query(query, new PowerMapper());
    }

    @Override
    public Power addPower(Power powerToAdd) {
        String query = "insert into Powers (`Name`) values (?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                toReturn.setString(1, powerToAdd.getName());

                return toReturn;
            }
        };

        jdbc.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        powerToAdd.setId(generatedId);

        return powerToAdd;
    }

    @Override
    public void editPower(Power editedPower) {
        String query = "Update Powers set `Name` = ? where Id = ?";

        jdbc.update(query, editedPower.getName(), editedPower.getId());
    }

    @Override
    public void deletePowerById(int powerId) {
        String deleteSighting = "delete Sightings\n"
                + "from Supeorganization\n"
                + "inner join Supes on Supeorganization.SupeId = Supes.Id\n"
                + "inner join Sightings on Supes.Id = Sightings.SupeId\n"
                + "where PowerId = ?";

        jdbc.update(deleteSighting, powerId);

//        String deleteSupeOrganization = "delete Supeorganization, Supes\n"
//                + "from Supeorganization\n"
//                + "inner join Supes on Supeorganization.SupeId = Supes.Id\n"
//                + "where PowerId = ?";
        

        //jdbc.update(deleteSupeOrganization, powerId);
        
                List<Supe> allSupsWithPower = getSupersByPower( powerId );

                
                for( Supe toDisassociate : allSupsWithPower ){
                    deleteOrgsForSupe( toDisassociate.getId());
                }

        String deleteSupe = "Delete from Supes where PowerId = ?";

        jdbc.update(deleteSupe, powerId);

        String deletePower = "Delete from Powers where Id = ?";

        jdbc.update(deletePower, powerId);

    }
    
        private List<Supe> getSupersByPower(int powerId) {
            String query = "Select Supes.Id as SupeId, Supes.`Name` as SupeName, Description, \n"
                    + "Powers.Id as PowerId, Powers.`Name` as PowerName\n"
                    + "from supes\n"
                    + "inner join powers on supes.powerId = powers.Id\n"
                    + "where Supes.powerId = ?;";
            return jdbc.query(query, new SupeMapper(), powerId);        }

    @Override
    public Organization getOrgById(int orgId) throws OrgDoesNotExistException {
        try {
            String query = "Select * from Organizations where Id = ?";

            return jdbc.queryForObject(query, new OrganizationMapper(), orgId);
        } catch (DataAccessException ex) {
            throw new OrgDoesNotExistException("Org with ID: " + orgId + " does not exist.");
        }
    }

    @Override
    public List<Organization> getAllOrgs() {
        String query = "Select * from Organizations";

        return jdbc.query(query, new OrganizationMapper());
    }

    @Override
    public Organization addOrg(Organization orgToAdd) {
        String query = "Insert into Organizations\n"
                + "(`Name`, `Description`, Address) \n"
                + "Values (?,?,?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                toReturn.setString(1, orgToAdd.getName());
                toReturn.setString(2, orgToAdd.getDescription());
                toReturn.setString(3, orgToAdd.getAddress());

                return toReturn;
            }
        };

        jdbc.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        orgToAdd.setId(generatedId);

        return orgToAdd;
    }

    @Override
    public void editOrg(Organization editedOrg) {
        String query = "Update Organizations set `Name` = ?, `Description` = ?, Address = ? Where Id = ?";

        jdbc.update(query, editedOrg.getName(), editedOrg.getDescription(), editedOrg.getAddress(), editedOrg.getId());
    }

    @Override
    public void deleteOrgById(int orgId) {
        String deleteSupeOrg = "Delete from SupeOrganization where OrgId = ?";

        jdbc.update(deleteSupeOrg, orgId);

        String deleteOrg = "Delete from Organizations where Id = ?";

        jdbc.update(deleteOrg, orgId);
    }

    @Override
    public Sighting getSightingById(int sightId) throws SightingDoesNotExistException {
//        try {
        String query = "Select s.Id AS SightId, `Date`, LocationId, SupeId, l.`name` AS LocationName, \n"
                + "Latitude, Longitude, supes.`name` AS SupeName\n"
                + "FROM sightings s\n"
                + "INNER JOIN locations l on s.LocationId = l.Id\n"
                + "INNER JOIN supes on s.SupeId = supes.ID WHERE s.Id = ?";

        return jdbc.queryForObject(query, new SightingMapper(), sightId);
//        } catch (DataAccessException ex) {
//            throw new SightingDoesNotExistException("Sighting with ID: " + sightId + " does not exist.");
//        }
    }

    @Override
    public List<Sighting> getAllSightings() {
        String query = "Select s.Id AS SightId, `Date`, LocationId, SupeId, l.`name` AS LocationName, \n"
                + "Latitude, Longitude, supes.`name` AS SupeName  \n"
                + "FROM sightings s\n"
                + "INNER JOIN locations l on s.LocationId = l.Id\n"
                + "INNER JOIN supes on s.SupeId = supes.ID;";

        return jdbc.query(query, new SightingMapper());
    }

    @Override
    public Sighting addSighting(Sighting sightingToAdd) {
        String query = "Insert into Sightings (`Date`, LocationId, SupeId) values (?, ?, ?)";

        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement toReturn = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                toReturn.setString(1, sightingToAdd.getDate().toString());
                toReturn.setInt(2, sightingToAdd.getLocation().getId());
                toReturn.setInt(3, sightingToAdd.getSupe().getId());

                return toReturn;
            }
        };

        jdbc.update(psc, holder);

        int generatedId = holder.getKey().intValue();

        sightingToAdd.setId(generatedId);

        return sightingToAdd;
    }

    @Override
    public void editSighting(Sighting editedSighting) {
        String query = "Update Sightings Set `Date` = ?, LocationId = ?, SupeId = ? Where Id = ?";

        jdbc.update(query, editedSighting.getDate(),
                editedSighting.getLocation().getId(),
                editedSighting.getSupe().getId(),
                editedSighting.getId());
    }

    @Override
    public void deleteSightingById(int sightId) {
        String deleteSighting = "Delete From Sightings Where Id = ?";

        jdbc.update(deleteSighting, sightId);
    }

    @Override
    public List<Supe> getSupesByLoc(int locId) {
        String query = "Select SupeId, LocationId, s.`Name` as SupeName, \n"
                + "`Description`, s.PowerId, p.`Name` as PowerName \n"
                + "from Sightings \n"
                + "inner join Supes s on Sightings.SupeId = s.Id\n"
                + "inner join Powers p on s.PowerId = p.Id\n"
                + "Where LocationId = ?;";

        return jdbc.query(query, new SupeMapper(), locId);
    }

    @Override
    public List<Location> getLocsBySupe(int supeId) {
        String query = "Select SupeId, l.Id as Id, `Name`, Address, Latitude, Longitude  \n"
                + "from Sightings s\n"
                + "inner join Locations l on s.LocationId = l.Id\n"
                + "where SupeId = ?";

        return jdbc.query(query, new LocationMapper(), supeId);
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        String query = "Select * from sightings where Date = ?";

        return jdbc.query(query, new SightingMapper(), java.sql.Date.valueOf(date));
    }

    @Override
    public List<Supe> getSupesByOrg(int orgId) {
        String query = "Select SupeId, OrgId, s.`Name` as SupeName, \n"
                + "`Description`, PowerId, p.`Name` as PowerName\n"
                + "from SupeOrganization so\n"
                + "inner join Supes s on so.SupeId = s.Id\n"
                + "inner join Powers p on s.PowerId = p.Id\n"
                + "Where OrgId = ?";

        return jdbc.query(query, new SupeMapper(), orgId);
    }

    @Override
    public List<Organization> getOrgsBySupe(int supeId) {
        String query = "Select SupeId, OrgId, `Name`, `Description`, Address \n"
                + "from SupeOrganization so\n"
                + "inner join Organizations o on so.OrgId = o.Id\n"
                + "where SupeId = ?";

        return jdbc.query(query, new OrganizationMapper(), supeId);

    }

    @Override
    public void addSupeOrgs(int supeId, int orgId) {
        String insert = "Insert into SupeOrganization (SupeId, OrgId) VALUES (?,?)";

        jdbc.update(insert, supeId, orgId);
    }

    @Override
    public void deleteOrgsForSupe(Integer id) {
        String delete = "DELETE FROM SupeOrganization WHERE SupeId = ?";

        jdbc.update(delete, id);
    }



    private static class SupeMapper implements RowMapper<Supe> {

        @Override
        public Supe mapRow(ResultSet rs, int i) throws SQLException {
            Supe supeToReturn = new Supe();
            supeToReturn.setId(rs.getInt("SupeId"));
            supeToReturn.setName(rs.getString("SupeName"));
            Power powerToAdd = new Power();
            powerToAdd.setId(rs.getInt("PowerId"));
            powerToAdd.setName(rs.getString("PowerName"));
            supeToReturn.setPower(powerToAdd);
            supeToReturn.setDescription(rs.getString("Description"));

            return supeToReturn;
        }
    }

    private static class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location locToReturn = new Location();
            locToReturn.setId(rs.getInt("Id"));
            locToReturn.setName(rs.getString("Name"));
            locToReturn.setAddress(rs.getString("Address"));
            locToReturn.setLatitude(rs.getBigDecimal("Latitude"));
            locToReturn.setLongitude(rs.getBigDecimal("Longitude"));

            return locToReturn;
        }
    }

    private static class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int i) throws SQLException {
            Power power = new Power();

            power.setId(rs.getInt("Id"));
            power.setName(rs.getString("Name"));

            return power;
        }
    }

    private static class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setId(rs.getInt("Id"));
            org.setName(rs.getString("Name"));
            org.setDescription(rs.getString("Description"));
            org.setAddress(rs.getString("Address"));

            return org;
        }

    }

    private static class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();

            s.setId(rs.getInt("SightId"));
            s.setDate(LocalDate.parse(rs.getString("Date")));
//            rs.getDate("Date").toLocalDate();
            Location l = new Location();
            l.setId(rs.getInt("LocationId"));
            l.setName(rs.getString("LocationName"));
            l.setLatitude(rs.getBigDecimal("Latitude"));
            l.setLongitude(rs.getBigDecimal("Longitude"));
            s.setLocation(l);
            Supe supe = new Supe();
            supe.setId(rs.getInt("SupeId"));
            supe.setName(rs.getString("SupeName"));
            s.setSupe(supe);

            return s;
        }

    }

}
