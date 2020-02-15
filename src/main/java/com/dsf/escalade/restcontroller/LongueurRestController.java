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

      try (LongueurDto longueurDto = longueurService.getOne(longueurId)) {
         return ResponseEntity.ok(longueurDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/longueur/complete/{longueurId}")
   public ResponseEntity<LongueurCompleteDto> getLongueurComplete(@PathVariable("longueurId") Integer longueurId) {

      try (LongueurCompleteDto longueurCompleteDto = longueurService.getFull(longueurId)) {
         return ResponseEntity.ok(longueurCompleteDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

}
