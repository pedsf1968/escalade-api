package com.dsf.escalade.controler;

import com.dsf.escalade.dao.contract.SiteDao;
import com.dsf.escalade.model.metier.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class SiteControler {

   @Autowired
   private SiteDao siteDao;

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
   public ResponseEntity<Object> addSite(@RequestBody Site site){
      Site siteAdded = siteDao.save(site);

      if (siteAdded == null)
         return ResponseEntity.noContent().build();

      URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(siteAdded.getId())
            .toUri();

      return ResponseEntity.created(location).build();
   }
}
