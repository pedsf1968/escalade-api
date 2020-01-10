package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.service.business.*;
import com.dsf.escalade.service.global.CommentService;
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

@Controller
@Slf4j
public class VoieController {
   private final UserService userService;
   private final SiteService siteService;
   private final TopoService topoService;
   private final SectorService sectorService;
   private final VoieService voieService;
   private final LongueurService longueurService;
   private final CotationService cotationService;
   private final CommentService commentService;



   public VoieController(UserService userService, SiteService siteService, TopoService topoService, SectorService sectorService, VoieService voieService, LongueurService longueurService, CotationService cotationService, CommentService commentService) {
      this.userService = userService;
      this.siteService = siteService;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.voieService = voieService;
      this.longueurService = longueurService;
      this.cotationService = cotationService;
      this.commentService = commentService;
   }

   @GetMapping("/voie/new/{parentId}")
   public String newVoie(@PathVariable("parentId") Integer parentId, Model model) {

      VoieDto voieDto = new VoieDto();
      voieDto.setParentId(parentId);

      if (siteService.getType(parentId).equals(SiteType.TOPO)){
         // Parent is a Topo
         TopoDto topoDto = topoService.getOne(parentId);
         voieDto.setAliasManager(topoDto.getAliasManager());
         voieDto.setLatitude(topoDto.getLatitude());
         voieDto.setLongitude(topoDto.getLongitude());
      } else {
         // Parent is a sector
         SectorDto sectorDto = sectorService.getOne(parentId);
         voieDto.setAliasManager(sectorDto.getAliasManager());
         voieDto.setLatitude(sectorDto.getLatitude());
         voieDto.setLongitude(sectorDto.getLongitude());
      }

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
      TopoDto topoDto;
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
         topoService.increaseLaneCounter(topoDto.getId());
         return PathTable.VOIE_UPDATE_R + voieService.save(voieDto);
      }

      //else return to parents
      if(sectorDto!=null) {
         return PathTable.SECTOR_UPDATE_R + parentId;
      } else {
         return PathTable.TOPO_UPDATE_R + parentId;
      }
   }

   @GetMapping("/voie/read/{voieId}")
   public String readVoie(@PathVariable("voieId") Integer voieId, Model model){

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieService.getOne(voieId));
      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR_LIST,longueurService.findByVoieId(voieId));
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());
      model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(voieId));

      return PathTable.VOIE_READ;
   }

   @GetMapping("/voie/edit/{voieId}")
   public String editVoie(@PathVariable("voieId") Integer voieId, Model model){

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieService.getOne(voieId));
      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR_LIST,longueurService.findByVoieId(voieId));
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.VOIE_UPDATE;
   }


   @PostMapping("/voie/update/{voieId}")
   public String updateVoie(@PathVariable("voieId") Integer voieId,@ModelAttribute("voieDto") @Valid VoieDto voieDto, @NotNull BindingResult bindingResultTopo, Model model) {
      if (bindingResultTopo.hasErrors()) {
         return PathTable.VOIE_UPDATE;
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
         return PathTable.SECTOR_UPDATE_R + parentId;
      } else {
         return PathTable.TOPO_UPDATE_R + parentId;
      }
   }

   @GetMapping("/voie/delete/{voieId}")
   public String deleteVoie(@PathVariable("voieId") Integer voieId, Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      VoieDto  voieDto = voieService.getOne(voieId);
      Integer parentId = voieDto.getParentId();
      TopoDto topoDto;
      SectorDto sectorDto = null;

      if(siteService.getType(parentId).equals(SiteType.TOPO)){
         topoDto = topoService.getOne(parentId);
      } else {
         sectorDto = sectorService.getOne(parentId);
         topoDto = topoService.getOne(sectorDto.getTopoId());
      }

      // verify that the manager is the Topo manager
      if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())){
         voieService.delete(voieDto);
         topoService.decreaseLaneCounter(topoDto.getId());
      }

      //return to parents
      if(sectorDto!=null) {
         return PathTable.SECTOR_UPDATE_R + sectorDto.getId();
      } else {
         return PathTable.TOPO_UPDATE_R + topoDto.getId();
      }
   }

}
