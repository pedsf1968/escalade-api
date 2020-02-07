package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.LongueurService;
import com.dsf.escalade.web.dto.LongueurCompleteDto;
import com.dsf.escalade.web.dto.LongueurDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LongueurRestController {
   private final LongueurService longueurService;

   @Autowired
   public LongueurRestController(LongueurService longueurService) {
      this.longueurService = longueurService;
   }

   @GetMapping("/longueur/summary/{longueurId}")
   public ResponseEntity<LongueurDto> getLongueurSummary(@PathVariable("longueurId") Integer longueurId) {
      LongueurDto longueurDto = longueurService.getOne(longueurId);

      if (longueurDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(longueurDto);
      }
   }


   @GetMapping("/longueur/complete/{longueurId}")
   public ResponseEntity<LongueurCompleteDto> getLongueurComplete(@PathVariable("longueurId") Integer longueurId) {
      LongueurCompleteDto longueurCompleteDto = longueurService.getFull(longueurId);

      if (longueurCompleteDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(longueurCompleteDto);
      }
   }

}
