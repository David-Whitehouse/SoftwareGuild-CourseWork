/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.SuperController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sg.SuperSightings.Dao.PowerDoesNotExistException;
import sg.SuperSightings.Models.Location;
import sg.SuperSightings.Models.Organization;
import sg.SuperSightings.Models.Power;
import sg.SuperSightings.Models.Sighting;
import sg.SuperSightings.Models.Supe;
import sg.SuperSightings.SupeService.SupeService;

/**
 *
 * @author ddubs
 */
@Controller
public class SuperController {
    
    @Autowired
    SupeService service;
    
    
    @GetMapping({"/", "/home"})
    public String displayHomePage() {

        return "home";
    }
    
    @GetMapping("/SupersManager")
    public String displaySuperManager(Model model){
        List<Supe> supes = service.getAllSupes();
        List<Power> powers = service.getAllPowers();
        List<Organization> orgs = service.getAllOrgs();
        
        model.addAttribute("supes", supes);
        model.addAttribute("powers", powers);
        model.addAttribute("orgs", orgs);

        return "supersManager";
    }
    
    @PostMapping("/addSuper")
    public String addSupe(Supe toAdd, HttpServletRequest request){
        String[] orgIds = request.getParameterValues("orgId");
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        Power powerToAdd = service.getPowerById(powerId);
        toAdd.setPower(powerToAdd);
        
        List<Organization> orgs = new ArrayList();
        
        for(String org : orgIds){
         orgs.add(service.getOrgById(Integer.parseInt(org)));  
        }
        
        service.addSupe( toAdd, orgs);
        
        
        return "redirect:/SupersManager";
    }
    
    
    @GetMapping("/editSuper/{id}")
    public String displayEditSupe(@PathVariable Integer id, Model model) {
        Supe toEdit = service.getSupeById(id);
        List<Power> powers = service.getAllPowers();
        List<Organization> orgs = service.getAllOrgs();
        
        model.addAttribute("powers", powers);
        model.addAttribute("toEdit", toEdit);
        model.addAttribute("orgs", orgs);
        
        
     return "EditSuper";   
    }
    
    @PostMapping("/editSuper/{id}")
    public String editSupe(@PathVariable Integer id, Supe edited, HttpServletRequest request){
        String[] orgIds = request.getParameterValues("orgId");
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        Power powerToAdd = service.getPowerById(powerId);
        edited.setPower(powerToAdd);
        
        service.editSupe(edited);
        service.removeOrgsforSupe(id);
        
        List<Organization> orgs = new ArrayList();

        if( orgIds.length > 0){
        for(String org : orgIds){
         orgs.add(service.getOrgById(Integer.parseInt(org)));  
        }
        service.addSupeOrgs(id, orgs);
        }
        
        return "redirect:/SupersManager";
    } 
    
    @GetMapping("/SuperPowerManager")
    public String displaySuperPowerManager(Model model){
        List<Power> powers = service.getAllPowers();
        
        model.addAttribute("powers", powers);
        
    return "superPowerManager";    
    }
    
    @PostMapping("/addPower")
    public String addPower (Power toAdd){
        
    service.addPower(toAdd);

    return "redirect:/SuperPowerManager";
    }
    
    @GetMapping("/editPower")
    public String displayEditPower(HttpServletRequest request, Model model){
        List<Power> powers = service.getAllPowers();
        int id = Integer.parseInt(request.getParameter("id"));
        Power toEdit = service.getPowerById(id);
        
        model.addAttribute("powers", powers);
        model.addAttribute("toEdit", toEdit);
        
        return "EditPower";
    }
    
    @PostMapping("/editPower")
    public String editPower(HttpServletRequest request, Power edited){
        
        
        service.editPower(edited);
        
        return "redirect:/SuperPowerManager";
    }
    
    @GetMapping("/deletePower")
    public String deletePower(HttpServletRequest request){
        Integer powerId = Integer.parseInt(request.getParameter("id"));
        
        service.deletePowerById(powerId);
        
        return "redirect:/SuperPowerManager";
    }
    
    
    @GetMapping("/deleteSuper")
    public String deleteSuper ( HttpServletRequest request){
        Integer supeId = Integer.parseInt(request.getParameter("id"));
        
        service.deleteSuperById(supeId);
        
        
    
        return "redirect:/SupersManager";
}
    
    @GetMapping("/SuperOrganizationManager")
    public String displayOrganizationManager(Model model){
        List<Organization> orgs = service.getAllOrgs();
        
        model.addAttribute("orgs", orgs);
        
        return "SuperOrganizationManager";
    }
    
    @PostMapping("/addOrg")
    public String addOrganization(Organization toAdd){
        service.addOrg(toAdd);
        
        return "redirect:/SuperOrganizationManager";
    }
    
    @GetMapping("/deleteOrg")
    public String deleteOrg ( HttpServletRequest request){
        Integer orgId = Integer.parseInt(request.getParameter("id"));
        
        service.deleteOrgById(orgId);
        
        
    
        return "redirect:/SuperOrganizationManager";
    
    }
    @GetMapping("/editOrg")
        public String displayEditOrg(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Organization toEdit = service.getOrgById(id);

        model.addAttribute("toEdit", toEdit);
        
        return "EditOrg";
    }
    
    
    @PostMapping("/editOrg")
    public String editOrg(HttpServletRequest request, Organization edited){
        
        
        service.editOrg(edited);
        
        return "redirect:/SuperOrganizationManager";
    }
    
    @GetMapping("/LocationManager")
    public String displayLocationManager(Model model){
        List<Location> locs = service.getAllLocs();
        
        model.addAttribute("locs", locs);
        
        return "LocationManager";
    }
    
    @PostMapping("/addLocation")
    public String addOrganization(Location toAdd){
        service.addLoc(toAdd);
        
        return "redirect:/LocationManager";
    }
    
    
    
    @GetMapping("/editLocation")
    public String displayEditLocation(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Location toEdit = service.getLocById(id);
 
        model.addAttribute("toEdit", toEdit);
        
        return "EditLocation";
    }
    
    @PostMapping("/editLocation")
    public String editLocation(HttpServletRequest request, Location edited){
        
        
        service.editLocation(edited);
        
        return "redirect:/LocationManager";
    }
    
    @GetMapping("/deleteLocation")
        public String deleteLocation (HttpServletRequest request){
        int locId = Integer.parseInt(request.getParameter("id"));
        
        service.deleteLocById(locId);
        
        return "redirect:/LocationManager";
    }
        
    @GetMapping("/SuperSightingsManager")
    public String displaysightingManager(Model model){
        List<Sighting> sights = service.getAllSights();
        List<Supe> supes = service.getAllSupes();
        List<Location> locs = service.getAllLocs();
        
        model.addAttribute("sights", sights);
        model.addAttribute("locs", locs);
        model.addAttribute("supes", supes);

        return "SuperSightingsManager";
                
    }
        
    @PostMapping("/addSighting")
    public String addSighting(Sighting toAdd, HttpServletRequest request){
//        LocalDate date = LocalDate.parse(request.getParameter("date"));
//        toAdd.setDate(toAdd);

          int locId = Integer.parseInt(request.getParameter("locId"));
          Location loc = service.getLocById(locId);
          
          int supeId = Integer.parseInt(request.getParameter("supeId"));
          Supe supe = service.getSupeById(supeId);
          toAdd.setLocation(loc);
          toAdd.setSupe(supe);
          
        service.addSighting(toAdd);
        
        return "redirect:/SuperSightingsManager";
    }
    
    @GetMapping("/editSighting")
    public String displayEditSighting(HttpServletRequest request, Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        Sighting toEdit = service.getSightingById(id);
        List<Supe> supes = service.getAllSupes();
        List<Location> locs = service.getAllLocs();
        
        model.addAttribute("toEdit", toEdit);
        model.addAttribute("locs", locs);
        model.addAttribute("supes", supes);
        
        
        return "EditSighting";
    }
    
    @PostMapping("/editSighting")
    public String editSighting(HttpServletRequest request, Sighting edited){
        int locId = Integer.parseInt(request.getParameter("locId"));
        int supeId = Integer.parseInt(request.getParameter("supeId"));
        Location locToAdd = service.getLocById(locId);
        Supe supeToAdd = service.getSupeById(supeId);
        edited.setLocation(locToAdd);
        edited.setSupe(supeToAdd);
        
        
        service.editSighting(edited);
        
        return "redirect:/SuperSightingsManager";
    }
    
    @GetMapping("/deleteSighting")
    public String deleteSighting(HttpServletRequest request){
        int sightId = Integer.parseInt(request.getParameter("id"));
        
        service.deleteSightingById(sightId);
        
        return "redirect:/SuperSightingsManager";
    }
    
}

