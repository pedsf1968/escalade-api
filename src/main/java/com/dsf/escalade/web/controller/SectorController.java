package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.AddressService;
import com.dsf.escalade.service.SectorService;
import com.dsf.escalade.service.TopoService;
import com.dsf.escalade.service.UserService;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.TopoDto;
import com.dsf.escalade.web.dto.UserDto;
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

@Controller
@Slf4j
public class SectorController {
   private final UserService userService;
   private final TopoService topoService;
   private final SectorService sectorService;
   private final AddressService addressService;
   private final List<String> statusList = Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

   @Autowired
   public SectorController(UserService userService, TopoService topoService, SectorService sectorService, AddressService addressService) {
      this.userService = userService;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.addressService = addressService;
   }

   @GetMapping("/sector/new/{id}")
   public String newSector(@PathVariable("id") Integer id, Model model) {
      SectorDto sectorDto = new SectorDto();
      sectorDto.setTopoId(id);

      model.addAttribute("sectorDto", sectorDto);

      return "sector/sector-add";
   }

   @PostMapping("/sector/add")
   public String addSector(@ModelAttribute("sectorDto") @Valid SectorDto sectorDto, @NotNull BindingResult bindingResultTopo, Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      Integer topoId = sectorDto.getTopoId();
      TopoDto topoDto = topoService.getOne(topoId);
      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      if (bindingResultTopo.hasErrors()) {
         return "sector/sector-add";
      }

      if (userDto.getEmail().equals(authentication.getName())){
         sectorService.save(sectorDto);
      }

      model.addAttribute("topoDto", topoDto);
      model.addAttribute("sectorDtoList", sectorService.findByTopoId(topoId));
      model.addAttribute("addressDto", addressService.getOne(topoDto.getAddressId()));
      model.addAttribute("statusList", statusList);

      return "topo/topo-update";
   }


   @GetMapping("/sector/edit/{id}")
   public String editSector(@PathVariable("id") Integer id, Model model) {
      SectorDto sectorDto = sectorService.getOne(id);

      model.addAttribute("sectorDto", sectorDto);

      return "sector/sector-update";
   }

   @PostMapping("/sector/update/{id}")
   public String updateSector(@PathVariable("id") Integer id, @ModelAttribute("sectorTdo") @Valid SectorDto sectorDto, @NonNull BindingResult bindingResult, Model model) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      UserDto userDto = userService.findByAlias(topoService.getOne(sectorDto.getTopoId()).getAliasManager());

      if(bindingResult.hasErrors()){
         return "sector/sector-update";
      }

      if (userDto.getEmail().equals(authentication.getName())){
         sectorService.save(sectorDto);
      }

      List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
      model.addAttribute("topoDtoList", topoDtoList);

      return "topo/topo-mylist";
   }

   @GetMapping("/sector/delete/{id}")
   public String deleteSector(@PathVariable("id") Integer id, Model model){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      SectorDto sectorDto = sectorService.getOne(id);
      UserDto userDto = userService.findByAlias(topoService.getOne(sectorDto.getTopoId()).getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())){
         sectorService.delete(sectorDto);
      }

      List<TopoDto> topoDtoList = topoService.findByManagerId(userDto.getId());
      model.addAttribute("topoDtoList", topoDtoList);

      return "topo/topo-mylist";
   }
}
