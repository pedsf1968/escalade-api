package com.dsf.escalade.controler;

import com.dsf.escalade.dao.contract.SiteDao;
import com.dsf.escalade.model.metier.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SiteControler {

   @Autowired
   private SiteDao siteDao;

   //Site
   @GetMapping(value = "Site")
   public List<Site> displayAllSite(){

      return siteDao.findAll();
   }

   //Site/{id}
   @GetMapping(value = "Site/{id}")
   public Site displaySite(@PathVariable int id){
      return siteDao.findById(id);
   }

   @PostMapping(value = "/Site")
   public void addSite(@RequestBody Site site){
      siteDao.save(site);
   }
}
