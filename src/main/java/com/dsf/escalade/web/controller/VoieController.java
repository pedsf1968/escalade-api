package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.*;
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
   private final AddressService addressService;
   private final List<String> statusList = Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());


   public VoieController(UserService userService, SiteService siteService, TopoService topoService, SectorService sectorService, VoieService voieService, AddressService addressService) {
      this.userService = userService;
      this.siteService = siteService;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.voieService = voieService;
      this.addressService = addressService;
   }

   @GetMapping("/voie/new/{id}")
   public String newVoie(@PathVariable("id") Integer parentId, Model model) {
      VoieDto voieDto = new VoieDto();
      voieDto.setParentId(parentId);

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);

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

      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())){
         voieService.save(voieDto);
      }

      if( sectorDto!=null){
         // the parent is a Sector
         model.addAttribute(PathTable.ATTRIBUTE_SECTOR, sectorDto);
         model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(parentId));
         return PathTable.SECTOR_UPDATE_R + parentId;

      } else {
         // the parent is a Topo
         model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
         model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(parentId));
         model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(parentId));
         model.addAttribute(PathTable.ATTRIBUTE_ADDRESS, addressService.getOne(topoDto.getAddressId()));
         model.addAttribute(PathTable.ATTRIBUTE_STATUS_LIST, statusList);

         return PathTable.TOPO_UPDATE_R + parentId;
      }

   }

}
