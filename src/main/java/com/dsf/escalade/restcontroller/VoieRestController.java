package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.web.dto.VoieDto;
import com.dsf.escalade.web.dto.VoieFullDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class VoieRestController {
   private final VoieService voieService;

   @Autowired
   public VoieRestController(VoieService voieService) {
      this.voieService = voieService;
   }


   @GetMapping("/voie/get/{voieId}")
   public ResponseEntity<VoieDto> getVoie(@PathVariable("voieId") Integer voieId) {

      try (VoieDto voieDto = voieService.getOne(voieId)) {
         return ResponseEntity.ok(voieDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @GetMapping("/voie/get/full/{voieId}")
   public ResponseEntity<VoieFullDto> getVoieFull(@PathVariable("voieId") Integer voieId) {
      try (VoieFullDto voieFullDto = voieService.getFull(voieId)) {
         return ResponseEntity.ok(voieFullDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

   @PostMapping(value="/voie/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<VoieDto> updateVoie(@RequestBody VoieDto voieDto) throws Exception {

      if(voieDto!=null) {
         log.info("/voie/update :" + voieDto);
         Integer voieId = voieService.save(voieDto);
         voieDto = voieService.getOne(voieId);
         return ResponseEntity.ok(voieDto);
      }

      return ResponseEntity.notFound().build();
   }

   @PostMapping(value="/voie/update/full", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<VoieFullDto> updateVoieFull(@RequestBody VoieFullDto voieFullDto) throws Exception {

      if(voieFullDto!=null) {
         log.info("/voie/update/full :" + voieFullDto);
         Integer voieId = voieService.saveFull(voieFullDto);
         voieFullDto = voieService.getFull(voieId);
         return ResponseEntity.ok(voieFullDto);
      }

      return ResponseEntity.notFound().build();
   }

}
