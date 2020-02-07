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
      TopoDto topoDto = topoService.getOne(topoId);

      if (topoDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(topoDto);
      }
   }


   @GetMapping("/topo/complete/{topoId}")
   public ResponseEntity<TopoCompleteDto> getTopoComplete(@PathVariable("topoId") Integer topoId) {
      TopoCompleteDto topoCompleteDto = topoService.getFull(topoId);

      if (topoCompleteDto == null) {
         return ResponseEntity.notFound().build();
      } else {
         return ResponseEntity.ok(topoCompleteDto);
      }
   }

}
