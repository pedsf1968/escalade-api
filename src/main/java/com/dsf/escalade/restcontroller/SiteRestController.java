package com.dsf.escalade.restcontroller;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.repository.business.SiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class SiteRestController {

   private final SiteRepository siteRepository;

   @Autowired
   public SiteRestController(SiteRepository siteRepository) {
      this.siteRepository = siteRepository;
   }

   @GetMapping(value = "/site")
   public List<Site> findAll(){
      return siteRepository.findAll();
   }

   @GetMapping(value = "/site/{id}")
   public Optional<Site> findById(@PathVariable Integer id){
      log.info("id : " + id + siteRepository.findById(1).toString());
      return siteRepository.findById(id);
   }

   @PostMapping(value = "/site")
   public ResponseEntity<Object> save(@RequestBody Site site){
      Site siteAdded = siteRepository.save(site);

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


