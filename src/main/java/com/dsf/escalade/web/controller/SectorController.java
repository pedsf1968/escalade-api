package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.AddressService;
import com.dsf.escalade.service.SectorService;
import com.dsf.escalade.service.TopoService;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@Slf4j
public class SectorController {
   private final TopoService topoService;
   private final SectorService sectorService;
   private final AddressService addressService;
   private final List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

   @Autowired
   public SectorController(TopoService topoService, SectorService sectorService, AddressService addressService) {
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.addressService = addressService;
   }

   @GetMapping("/sector/new/{id}")
   public String newSector( @PathVariable("id") Integer id, Model model) {
      SectorDto sectorDto = new SectorDto();
      sectorDto.setTopoId(id);

      model.addAttribute("sectorDto", sectorDto);

      return "sector/sector-add";
   }

   @PostMapping("/sector/add")
   public String addSector(@ModelAttribute("sectorDto") @Valid SectorDto sectorDto, @NotNull BindingResult bindingResultTopo, Model model) {
      Integer topoId = sectorDto.getTopoId();
      TopoDto topoDto = topoService.getOne(topoId);

      if (bindingResultTopo.hasErrors() ) {
         return "sector/sector-add";
      }

      sectorService.save(sectorDto);

      model.addAttribute("topoDto", topoDto);
      model.addAttribute("sectorDtoList", sectorService.findByTopoId(topoId));
      model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
      model.addAttribute("statusList", statusList);

      return "topo/topo-update";
   }
}
