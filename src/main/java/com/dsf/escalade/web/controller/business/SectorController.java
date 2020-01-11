package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.business.CotationService;
import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.service.global.AddressService;
import com.dsf.escalade.service.global.CommentService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.TopoDto;
import com.dsf.escalade.web.dto.VoieDto;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

@Slf4j
@Controller
public class SectorController {
   private final UserService userService;
   private final TopoService topoService;
   private final SectorService sectorService;
   private final VoieService voieService;
   private final CotationService cotationService;
   private final CommentService commentService;
   private final AddressService addressService;
   private final List<String> statusList = Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

   @Autowired
   public SectorController(UserService userService, TopoService topoService, SectorService sectorService, VoieService voieService, CotationService cotationService, CommentService commentService, AddressService addressService) {
      this.userService = userService;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.voieService = voieService;
      this.cotationService = cotationService;
      this.commentService = commentService;
      this.addressService = addressService;
   }

   @GetMapping("/sector/new/{topoId}")
   public String newSector(@PathVariable("topoId") Integer topoId, Model model) {
      TopoDto topoDto = topoService.getOne(topoId);
      SectorDto sectorDto = new SectorDto();

      sectorDto.setTopoId(topoId);
      // set default latitude and longitude parent's latitude et longitude
      sectorDto.setLatitude(topoDto.getLatitude());
      sectorDto.setLongitude(topoDto.getLongitude());
      sectorDto.setAliasManager(topoDto.getAliasManager());
      log.info("/sector/new sector : " + sectorDto);
      model.addAttribute(PathTable.ATTRIBUTE_SECTOR, sectorDto);

      return PathTable.SECTOR_ADD;
   }

   @PostMapping("/sector/add")
   public String addSector(@ModelAttribute("sectorDto") @Valid SectorDto sectorDto, @NotNull BindingResult bindingResultTopo, Model model) {
      if (bindingResultTopo.hasErrors()) {
         return PathTable.SECTOR_ADD;
      }

      // verify that the manager is the Topo manager
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(userService.findByAlias(sectorDto.getAliasManager()).getEmail().equals(authentication.getName())) {
         return PathTable.SECTOR_UPDATE_R + sectorService.save(sectorDto);
      }

      return PathTable.TOPO_UPDATE_R + sectorDto.getTopoId();
   }

   @GetMapping("/sector/read/{sectorId}")
   public String readSector(@PathVariable("sectorId") Integer sectorId, Model model) {
      // we fetch the sector
      SectorDto sectorDto = sectorService.getOne(sectorId);

      // we fetch the topo parent and siblings for tab informations
      Integer topoId = sectorDto.getTopoId();
      TopoDto topoDto = topoService.getOne(topoId);


      model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
      model.addAttribute(PathTable.ATTRIBUTE_SECTOR, sectorDto);
      model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
      model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(sectorId));
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());
      model.addAttribute(PathTable.ATTRIBUTE_COMMENT_LIST, commentService.getBySiteId(sectorId));

      return PathTable.SECTOR_READ;
   }

   @GetMapping("/sector/edit/{sectorId}")
   public String editSector(@PathVariable("sectorId") Integer sectorId, Model model) {
      SectorDto sectorDto = sectorService.getOne(sectorId);

      // we fetch the topo parent and siblings for tab informations
      Integer topoId = sectorDto.getTopoId();
      TopoDto topoDto = topoService.getOne(topoId);

      model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
      model.addAttribute(PathTable.ATTRIBUTE_SECTOR, sectorDto);
      model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
      model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(sectorId));
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.SECTOR_UPDATE;
   }

   @PostMapping("/sector/update/{sectorId}")
   public String updateSector(@PathVariable("sectorId") Integer sectorId,
                              @ModelAttribute("sectorTdo") @Valid SectorDto sectorDto, @NonNull BindingResult bindingResult, Model model) {

      if(bindingResult.hasErrors()){
         // we fetch the topo parent and siblings for tab informations
         Integer topoId = sectorDto.getTopoId();
         TopoDto topoDto = topoService.getOne(topoId);

         model.addAttribute(PathTable.ATTRIBUTE_TOPO, topoDto);
         model.addAttribute(PathTable.ATTRIBUTE_SECTOR_LIST, sectorService.findByTopoId(topoId));
         model.addAttribute(PathTable.ATTRIBUTE_VOIE_LIST, voieService.findByParentId(sectorId));

         return PathTable.SECTOR_UPDATE;
      }

      // verify that the manager is the Topo manager
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(userService.findByAlias(sectorDto.getAliasManager()).getEmail().equals(authentication.getName())) {
         sectorService.save(sectorDto);
      }

      return PathTable.TOPO_UPDATE_R + sectorDto.getTopoId();
   }

   @GetMapping("/sector/delete/{sectorId}")
   public String deleteSector(@PathVariable("sectorId") Integer sectorId, Model model){
      SectorDto sectorDto = sectorService.getOne(sectorId);
      Integer topoId = sectorDto.getTopoId();

      // verify that the manager is the Topo manager
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if(userService.findByAlias(sectorDto.getAliasManager()).getEmail().equals(authentication.getName())) {
         sectorService.delete(sectorDto);

         // we delete all Lanes of the Sector
         for(VoieDto voieDto : voieService.findByParentId(sectorId)){
            voieService.delete(voieDto);
         }
      }

      return PathTable.TOPO_UPDATE_R + topoId;
   }
}
