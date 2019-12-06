package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.*;
import com.dsf.escalade.web.dto.AddressDto;
import com.dsf.escalade.web.dto.SecteurDto;
import com.dsf.escalade.web.dto.TopoDto;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@Slf4j
public class TopoController {

    @PersistenceContext
    private final EntityManager entityManager;
    private final UserService userService;
    private final AddressService addressService;
    private final TopoService topoService;
    private final SecteurService secteurService;
    private final CommentService commentService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public TopoController(EntityManager entityManager, UserService userService, AddressService addressService, TopoService topoService, SecteurService secteurService, CommentService commentService) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.secteurService = secteurService;
        this.commentService = commentService;
    }

    @GetMapping("/topo/all")
    public String listTopo(Model model) {
        List<TopoDto> topoDtoList = topoService.findAll();
        model.addAttribute("topoDtoList", topoDtoList);
        return "topo/topo-all";
    }

    @GetMapping("/topo/mylist")
    public String myListTopo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());

        model.addAttribute("topoDtoList", topoDtoList);

        return "topo/topo-mylist";
    }

    @GetMapping("/topo/new")
    public String newTopo( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = new TopoDto();


        topoDto.setAliasManager(userService.findByEmail(authentication.getName()).getAlias());
        model.addAttribute("topoDto", topoDto);
        model.addAttribute("addressDto", new AddressDto());
        model.addAttribute("statusList", statusList);

        return "topo/topo-update";
    }




    @GetMapping("/topo/read/{id}")
    public String readTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);
        List<SecteurDto> secteurDtoList = secteurService.findByTopoId(id);

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("secteurDtoList", secteurDtoList);
        model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
        model.addAttribute("commentDtoList", commentService.getBySiteId(id));

        return "topo/topo-read";
    }

    @GetMapping("/topo/update/{id}")
    public String updateTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);
        List<SecteurDto> secteurDtoList = secteurService.findByTopoId(id);

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("secteurDtoList", secteurDtoList);
        model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
        model.addAttribute("commentDtoList", commentService.getBySiteId(id));
        model.addAttribute("statusList", statusList);

        return "topo/topo-update";
    }
    @PostMapping("/topo/update/{id}")
    public String createTopo(@PathVariable("id") Integer id,
                             @ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                             @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        if(bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()){
            return "topo/topo-update";
        }

        Integer addressId = addressService.save(addressDto);
        topoDto.setAddressId(addressId);
        topoService.save(topoDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());

        model.addAttribute("topoDtoList", topoDtoList);
        return "topo/topo-mylist";
    }

    @GetMapping("/topo/delete/{id}")
    public String deleteTopo(@PathVariable("id") Integer id, Model model) {
        //TODO

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        return "topo/topo-mylist";
    }
}
