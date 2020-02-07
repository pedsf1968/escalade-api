package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.VoieService;
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
      VoieDto voieDto = voieService.getOne(voieId);

      if (voieDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(voieDto);
      }
   }


   @GetMapping("/voie/complete/{voieId}")
   public ResponseEntity<VoieDto> getVoieComplete(@PathVariable("voieId") Integer voieId) {
      VoieDto voieCompleteDto = voieService.getOne(voieId);

      if (voieCompleteDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(voieCompleteDto);
      }
   }

}
