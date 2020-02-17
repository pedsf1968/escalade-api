package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.SectorFullDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SectorRestController {
   private final SectorService sectorService;

   @Autowired
   public SectorRestController(SectorService sectorService) {
      this.sectorService = sectorService;
   }


   @GetMapping("/sector/get/{sectorId}")
   public ResponseEntity<SectorDto> getSector(@PathVariable("sectorId") Integer sectorId) {

      try(SectorDto sectorDto = sectorService.getOne(sectorId)) {
         return ResponseEntity.ok(sectorDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/sector/get/full/{sectorId}")
   public ResponseEntity<SectorFullDto> getSectorFull(@PathVariable("sectorId") Integer sectorId) {

      try (SectorFullDto sectorFullDto = sectorService.getFull(sectorId)) {
         return ResponseEntity.ok(sectorFullDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @PostMapping(value="/sector/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SectorDto> updateSector(@RequestBody SectorDto sectorDto) throws Exception {

      if(sectorDto!=null) {
         log.info("/sector/update :" + sectorDto);
         Integer sectorId = sectorService.save(sectorDto);
         sectorDto = sectorService.getOne(sectorId);
         return ResponseEntity.ok(sectorDto);
      }

      return ResponseEntity.notFound().build();
   }

   @PostMapping(value="/sector/update/full", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<SectorFullDto> updateSectorFull(@RequestBody SectorFullDto sectorFullDto) throws Exception {

      if(sectorFullDto!=null) {
         log.info("/sector/update/full :" + sectorFullDto);
         Integer sectorId = sectorService.saveFull(sectorFullDto);
         sectorFullDto = sectorService.getFull(sectorId);
         return ResponseEntity.ok(sectorFullDto);
      }

      return ResponseEntity.notFound().build();
   }

}
