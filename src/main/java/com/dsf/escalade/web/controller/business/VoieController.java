package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.*;
import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.TopoDto;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.dto.VoieDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class VoieController {
   private final UserService userService;
   private final SiteService siteService;
   private final TopoService topoService;
   private final SectorService sectorService;
   private final VoieService voieService;
   private final CotationService cotationService;
   private final AddressService addressService;
   private final List<String> statusList = Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());


   public VoieController(UserService userService, SiteService siteService, TopoService topoService, SectorService sectorService, VoieService voieService, CotationService cotationService, AddressService addressService) {
      this.userService = userService;
      this.siteService = siteService;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.voieService = voieService;
      this.cotationService = cotationService;
      this.addressService = addressService;
   }

   @GetMapping("/voie/new/{id}")
   public String newVoie(@PathVariable("id") Integer parentId, Model model) {
      VoieDto voieDto = new VoieDto();
      voieDto.setParentId(parentId);

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.VOIE_ADD;
   }

   @PostMapping("/voie/add")
   public String addVoie(@ModelAttribute("voieDto") @Valid VoieDto voieDto, @NotNull BindingResult bindingResultTopo, Model model) {
      if (bindingResultTopo.hasErrors()) {
         return PathTable.VOIE_ADD;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Integer parentId = voieDto.getParentId();
      TopoDto topoDto = null;
      SectorDto sectorDto = null;


      if(siteService.getType(parentId).equals(SiteType.TOPO)){
         topoDto = topoService.getOne(parentId);
      } else {
         sectorDto = sectorService.getOne(parentId);
         topoDto = topoService.getOne(sectorDto.getTopoId());
      }

      //get the manager of the topo
      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())){
         return PathTable.VOIE_UPDATE_R + voieService.save(voieDto);
      }

      //return to parents
      if(sectorDto!=null) {
         return PathTable.SECTOR_UPDATE_R + sectorDto.getId();
      } else {
         return PathTable.TOPO_UPDATE_R + topoDto.getId();
      }
   }

   @GetMapping("/voie/read/{id}")
   public String readVoie(@PathVariable("id") Integer voieId, Model model){
      VoieDto voieDto = voieService.getOne(voieId);

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.VOIE_READ;
   }

   @GetMapping("/voie/edit/{id}")
   public String editVoie(@PathVariable("id") Integer voieId, Model model){
      VoieDto voieDto = voieService.getOne(voieId);

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.VOIE_UPDATE;
   }


   @PostMapping("/voie/update/{id}")
   public String updateVoie(@ModelAttribute("voieDto") @Valid VoieDto voieDto, @NotNull BindingResult bindingResultTopo, Model model) {
      if (bindingResultTopo.hasErrors()) {
         return PathTable.VOIE_ADD;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Integer parentId = voieDto.getParentId();
      TopoDto topoDto = null;
      SectorDto sectorDto = null;

      if(siteService.getType(parentId).equals(SiteType.TOPO)){
         topoDto = topoService.getOne(parentId);
      } else {
         sectorDto = sectorService.getOne(parentId);
         topoDto = topoService.getOne(sectorDto.getTopoId());
      }

      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())){
         voieService.save(voieDto);
      }

      //return to parents
      if(sectorDto!=null) {
         return PathTable.SECTOR_UPDATE_R + sectorDto.getId();
      } else {
         return PathTable.TOPO_UPDATE_R + topoDto.getId();
      }
   }

}
