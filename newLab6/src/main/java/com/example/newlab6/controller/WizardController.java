package com.example.newlab6.controller;

import com.example.newlab6.pojo.Wizard;
import com.example.newlab6.pojo.Wizards;
import com.example.newlab6.repository.WizardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class WizardController {
    @Autowired
    private WizardService wizardService;
    private Wizards wizards2 = new Wizards();
    @RequestMapping(value = "/wizards", method = RequestMethod.GET)
    public List<Wizard> getWizard() {
        List<Wizard> wizards = wizardService.retrieveWizard();

        wizards2.setModel((ArrayList<Wizard>) wizards);
        return wizards2.getModel();
    }

    @RequestMapping(value ="/addWizard", method = RequestMethod.POST)
    public ResponseEntity<Wizard> createWizard( @RequestBody MultiValueMap<String, String> n)
    {
        Map<String, String> d = n.toSingleValueMap();
        Wizard wizard = wizardService.createWizard(new Wizard(null,d.get("sex"),d.get("name"),d.get("school"),d.get("house"),Integer.parseInt(d.get("money")),d.get("position")));
        System.out.println(wizard);
        return ResponseEntity.ok(wizard);
    }
    @RequestMapping(value ="/updateWizard", method = RequestMethod.POST)
    public boolean updateWizard(@RequestBody MultiValueMap<String, String> n) {
        Map<String, String> d = n.toSingleValueMap();
        Wizard wizard = wizardService.updateWizard(new Wizard(d.get("_id"),d.get("sex"),d.get("name"),d.get("school"),d.get("house"),Integer.parseInt(d.get("money")),d.get("position")));
        return true;
    }
    @RequestMapping(value ="/deleteWizard", method = RequestMethod.POST)
    public boolean deleteWizard( @RequestBody MultiValueMap<String, String> n){
        Map<String, String> d = n.toSingleValueMap();
        Wizard wizard = wizardService.findbyIDWizard(d.get("_id"));
        System.out.println(wizard);
        boolean wiz = wizardService.deleteWizard(wizard);
        if(wiz){
            return true;
        }
        return false;
    }
}
