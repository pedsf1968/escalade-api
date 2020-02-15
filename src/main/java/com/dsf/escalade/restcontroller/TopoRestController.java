package com.dsf.escalade.restcontroller;

import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.web.dto.TopoCompleteDto;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TopoRestController {
   private final TopoService topoService;

   @Autowired
   public TopoRestController(TopoService topoService) {
      this.topoService = topoService;
   }

   @GetMapping("/topo/summary/{topoId}")
   public ResponseEntity<TopoDto> getTopoSummary(@PathVariable("topoId") Integer topoId) {

      try(TopoDto topoDto = topoService.getOne(topoId)) {
         return ResponseEntity.ok(topoDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }


   @GetMapping("/topo/complete/{topoId}")
   public ResponseEntity<TopoCompleteDto> getTopoComplete(@PathVariable("topoId") Integer topoId) {

      try(TopoCompleteDto topoCompleteDto = topoService.getFull(topoId)) {
         return ResponseEntity.ok(topoCompleteDto);
      } catch (Exception e) {
         return ResponseEntity.notFound().build();
      }
   }

}
