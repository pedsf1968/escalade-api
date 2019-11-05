package com.dsf.escalade.restcontroller;

import com.dsf.escalade.dao.metier.SiteDao;
import com.dsf.escalade.model.metier.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class SiteRestController {

   @Autowired
   private SiteDao siteDao;

   @GetMapping(value = "/site")
   public List<Site> findAll(){
      return siteDao.findAll();
   }

   @GetMapping(value = "/site/{id}")
   public Optional<Site> findById(@PathVariable int id){
      log.info("id : " + id + siteDao.findById(1).toString());
      return siteDao.findById(id);
   }

   @PostMapping(value = "/site")
   public ResponseEntity<Object> save(@RequestBody Site site){
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


