package com.dsf.escalade.web.controller.business;

import com.dsf.escalade.service.business.CotationService;
import com.dsf.escalade.service.business.LongueurService;
import com.dsf.escalade.service.business.SpitService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.LongueurDto;
import com.dsf.escalade.web.dto.UserDto;
import com.dsf.escalade.web.dto.VoieDto;
import lombok.NonNull;
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
public class LongueurController {
   private final VoieService voieService;
   private final LongueurService longueurService;
   private final SpitService spitService;
   private final CotationService cotationService;
   private final UserService userService;

   public LongueurController(VoieService voieService, LongueurService longueurService, SpitService spitService, CotationService cotationService, UserService userService) {
      this.voieService = voieService;
      this.longueurService = longueurService;
      this.spitService = spitService;
      this.cotationService = cotationService;
      this.userService = userService;
   }


   @GetMapping("/longueur/new/{voieId}")
   public String newLongueur(@PathVariable("voieId") Integer voieId, Model model) {
      LongueurDto longueurDto = new LongueurDto();
      longueurDto.setVoieId(voieId);

      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR, longueurDto);
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.LONGUEUR_ADD;
   }

   @PostMapping("/longueur/add")
   public String addLongueur(@ModelAttribute("LongueurDto") @Valid LongueurDto longueurDto, @NotNull BindingResult bindingResult, Model model) {
      if (bindingResult.hasErrors()) {
         return PathTable.LONGUEUR_ADD;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Integer voieId = longueurDto.getVoieId();
      VoieDto voieDto = voieService.getOne(voieId);
      Integer parentId = voieDto.getParentId();

      return PathTable.LONGUEUR_UPDATE_R + longueurService.save(longueurDto);
   }

   @GetMapping("/longueur/read/{longueurId}")
   public String readLane(@PathVariable("longueurId") Integer longueurId, Model model) {
      LongueurDto longueurDto = longueurService.getOne(longueurId);
      Integer voieId = longueurDto.getVoieId();
      VoieDto voieDto = voieService.getOne(voieId);

      log.info("\n\nINFO :"  + spitService.getLastSpitNumber(2));

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);
      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR, longueurDto);
      model.addAttribute(PathTable.ATTRIBUTE_SPIT_LIST, spitService.findByLongueurId(longueurId));
      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR_LIST,longueurService.findByVoieId(voieId));
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.LONGUEUR_READ;
   }

   @GetMapping("/longueur/edit/{longueurId}")
   public String editLane(@PathVariable("longueurId") Integer longueurId, Model model) {
      LongueurDto longueurDto = longueurService.getOne(longueurId);
      Integer voieId = longueurDto.getVoieId();
      VoieDto voieDto = voieService.getOne(voieId);

      model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);
      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR, longueurDto);
      model.addAttribute(PathTable.ATTRIBUTE_SPIT_LIST, spitService.findByLongueurId(longueurId));
      model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR_LIST,longueurService.findByVoieId(voieId));
      model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

      return PathTable.LONGUEUR_UPDATE;
   }

   @PostMapping("/longueur/update/{longueurId}")
   public String updateSector(@PathVariable("longueurId") Integer longueurId, @ModelAttribute("longueurDto") @Valid LongueurDto longueurDto, @NonNull BindingResult bindingResult, Model model) {
      Integer voieId = longueurDto.getVoieId();
      VoieDto voieDto = voieService.getOne(voieId);

      if(bindingResult.hasErrors()){
         model.addAttribute(PathTable.ATTRIBUTE_VOIE, voieDto);
         model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR, longueurDto);
         model.addAttribute(PathTable.ATTRIBUTE_SPIT_LIST, spitService.findByLongueurId(longueurId));
         model.addAttribute(PathTable.ATTRIBUTE_LONGUEUR_LIST,longueurService.findByVoieId(voieId));
         model.addAttribute(PathTable.ATTRIBUTE_COTATION_LIST, cotationService.findAll());

         return PathTable.LONGUEUR_UPDATE;
      }

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      UserDto userDto = userService.findByAlias(voieDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())){
         longueurService.save(longueurDto);
      }

      return PathTable.VOIE_UPDATE_R + voieId;
   }

   @GetMapping("/longueur/delete/{longueurId}")
   public String deleteSector(@PathVariable("longueurId") Integer longueurId, Model model){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      LongueurDto longueurDto = longueurService.getOne(longueurId);
      Integer voieId = longueurDto.getVoieId();
      VoieDto voieDto = voieService.getOne(voieId);

      UserDto userDto = userService.findByAlias(voieDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())){
         longueurService.delete(longueurDto);
      }

      return PathTable.VOIE_UPDATE_R + voieId;
   }

}
