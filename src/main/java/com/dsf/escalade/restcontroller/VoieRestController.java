package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.web.dto.VoieCompleteDto;
import com.dsf.escalade.web.dto.VoieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class VoieRestController {
   private final VoieService voieService;

   @Autowired
   public VoieRestController(VoieService voieService) {
      this.voieService = voieService;
   }


   @GetMapping("/voie/summary/{voieId}")
   public ResponseEntity<VoieDto> getVoieSummary(@PathVariable("voieId") Integer voieId) {

      try (VoieDto voieDto = voieService.getOne(voieId)) {
         return ResponseEntity.ok(voieDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/voie/complete/{voieId}")
   public ResponseEntity<VoieCompleteDto> getVoieComplete(@PathVariable("voieId") Integer voieId) {
      try (VoieCompleteDto voieCompleteDto = voieService.getFull(voieId)) {
         return ResponseEntity.ok(voieCompleteDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

}
