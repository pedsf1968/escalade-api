package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.web.dto.SectorCompleteDto;
import com.dsf.escalade.web.dto.SectorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SectorRestController {
   private final SectorService sectorService;

   @Autowired
   public SectorRestController(SectorService sectorService) {
      this.sectorService = sectorService;
   }


   @GetMapping("/sector/summary/{sectorId}")
   public ResponseEntity<SectorDto> getSectorSummary(@PathVariable("sectorId") Integer sectorId) {
      SectorDto sectorDto = sectorService.getOne(sectorId);

      if (sectorDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(sectorDto);
      }
   }


   @GetMapping("/sector/complete/{sectorId}")
   public ResponseEntity<SectorCompleteDto> getSectorComplete(@PathVariable("sectorId") Integer sectorId) {
      SectorCompleteDto sectorCompleteDto = sectorService.getFull(sectorId);

      if (sectorCompleteDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(sectorCompleteDto);
      }
   }
}
