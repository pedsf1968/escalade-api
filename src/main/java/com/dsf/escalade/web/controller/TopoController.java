package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.service.*;
import com.dsf.escalade.web.dto.AddressDto;
import com.dsf.escalade.web.dto.CommentDto;
import com.dsf.escalade.web.dto.SectorDto;
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
import java.sql.Date;
import java.time.LocalDate;
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
    private final SectorService sectorService;
    private final CommentService commentService;
    private final  List<String> statusList =  Stream.of(StatusType.values()).map(Enum::name).collect(Collectors.toList());

    @Autowired
    public TopoController(EntityManager entityManager, UserService userService, AddressService addressService, TopoService topoService, SectorService sectorService, CommentService commentService) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.sectorService = sectorService;
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
        AddressDto addressDto = new AddressDto();

        topoDto.setAliasManager(userService.findByEmail(authentication.getName()).getAlias());
        topoDto.setDate(Date.valueOf(LocalDate.now()));
        model.addAttribute("topoDto", topoDto);
        model.addAttribute("addressDto", addressDto);
        model.addAttribute("statusList", statusList);

        return "topo/topo-add";
    }

    @PostMapping("/topo/add")
    public String addTopo(@ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                          @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()) {
            model.addAttribute("statusList", statusList);
            return "topo/topo-add";
        }

        // verify that the manager is the Topo manager
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())) {
            topoDto.setAddressId(addressService.save(addressDto));
            topoService.save(topoDto);
        }

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        model.addAttribute("topoDtoList", topoDtoList);
        return "topo/topo-mylist";
    }

    @GetMapping("/topo/read/{id}")
    public String readTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(id);

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("sectorDtoList", sectorDtoList);
        model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
        model.addAttribute("commentDtoList", commentService.getBySiteId(id));
        model.addAttribute("commentaire", new String());

        return "topo/topo-read";
    }

    @GetMapping("/topo/edit/{id}")
    public String editTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(id);

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("sectorDtoList", sectorDtoList);
        model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
        model.addAttribute("statusList", statusList);

        return "topo/topo-update";
    }

    @PostMapping("/topo/update/{id}")
    public String updateTopo(@PathVariable("id") Integer id, @ModelAttribute("topoDto") @Valid TopoDto topoDto, @NotNull  BindingResult bindingResultTopo,
                             @ModelAttribute("addressDto") @Valid AddressDto addressDto, @NonNull BindingResult bindingResultAddress, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(bindingResultTopo.hasErrors() || bindingResultAddress.hasErrors()){
            List<SectorDto> sectorDtoList = sectorService.findByTopoId(id);
            model.addAttribute("sectorDtoList", sectorDtoList);
            model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
            model.addAttribute("statusList", statusList);
            return "topo/topo-update";
        }

        // verify that the manager is the Topo manager
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())) {
            topoDto.setAddressId(addressService.save(addressDto));
            topoService.save(topoDto);
        }

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        model.addAttribute("topoDtoList", topoDtoList);

        return "topo/topo-mylist";
    }

    @GetMapping("/topo/delete/{id}")
    public String deleteTopo(@PathVariable("id") Integer id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(id);

        // verify that the manager is the Topo manager
        if(userService.findByAlias(topoDto.getAliasManager()).getEmail().equals(authentication.getName())){
            topoService.delete(topoDto);
        }

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(authentication.getName()).getId());
        model.addAttribute("topoDtoList", topoDtoList);

        return "topo/topo-mylist";
    }

    @PostMapping("/topo/comment/{topoId}")
public String addTopoComment(@PathVariable("topoId") Integer topoId, @ModelAttribute("commentaire") String commentaire, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TopoDto topoDto = topoService.getOne(topoId);
        List<SectorDto> sectorDtoList = sectorService.findByTopoId(topoId);

        //don't save anonymous comment
        if(!authentication.getName().equals("anonymousUser")) {
            CommentDto commentDto = new CommentDto();
            commentDto.setSiteId(topoId);
            commentDto.setText(commentaire);
            log.info("User : " + authentication.getName() + " add comment");
            commentDto.setAlias(userService.findByEmail(authentication.getName()).getAlias());
            commentService.save(commentDto);
        }

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("sectorDtoList", sectorDtoList);
        model.addAttribute("addressDto",addressService.getOne(topoDto.getAddressId()));
        model.addAttribute("commentDtoList", commentService.getBySiteId(topoId));
        model.addAttribute("commentaire",new String());

        return "topo/topo-read";
    }


}
