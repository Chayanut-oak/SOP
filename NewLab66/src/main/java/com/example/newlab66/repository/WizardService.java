package com.example.newlab66.repository;

import com.example.newlab66.pojo.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableCaching
@Service
public class WizardService {
    @Autowired
    private WizardRepository repository;


    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }
    @Cacheable(value="mywizard",key="'wizard'")
    public List<Wizard> retrieveWizard() {
        return repository.findAll();
    }
    @CacheEvict(value="mywizard" ,key="'wizard'")
    public Wizard createWizard(Wizard wizard){
        return repository.save(wizard);
    }
    @CachePut(value="mywizard" ,key="'wizard'")
    public List<Wizard> updateWizard(Wizard wizard){
        repository.save(wizard);
        return repository.findAll();
    }
    @CacheEvict(value="mywizard" ,key="'wizard'")
    public boolean  deleteWizard(Wizard wizard){
        try { repository.delete(wizard); return true; }
        catch (Exception e){ return false;}
    }

    public Wizard findbyIDWizard(String id){
        return repository.findByID(id);
    }
}
