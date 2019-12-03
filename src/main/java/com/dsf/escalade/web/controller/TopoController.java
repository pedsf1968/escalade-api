package com.dsf.escalade.web.controller;

import com.dsf.escalade.service.AddressService;
import com.dsf.escalade.service.CommentService;
import com.dsf.escalade.service.TopoService;
import com.dsf.escalade.service.UserService;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Controller
@Slf4j
public class TopoController {

    @PersistenceContext
    private final EntityManager entityManager;
    private final UserService userService;
    private final AddressService addressService;
    private final TopoService topoService;
    private final CommentService commentService;

    @Autowired
    public TopoController(EntityManager entityManager, UserService userService, AddressService addressService, TopoService topoService, CommentService commentService) {
        this.entityManager = entityManager;
        this.userService = userService;
        this.addressService = addressService;
        this.topoService = topoService;
        this.commentService = commentService;
    }

    @GetMapping("/topo/list")
    public String listTopo(Model model) {
        List<TopoDto> topoDtoList = topoService.findAll();

        model.addAttribute("topoDtoList", topoDtoList);
        return "topo/topo-list";
    }

    @GetMapping("/my/topo/list")
    public String myListTopo(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        List<TopoDto> topoDtoList = topoService.findByManagerId(userService.findByEmail(currentPrincipalName).getId());

        model.addAttribute("topoDtoList", topoDtoList);
        return "topo/topo-list";
    }


    @GetMapping("/topo/read/{id}")
    public String readTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto = topoService.getOne(id);

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("addressDto",addressService.getOne(topoDto.getId()));
        model.addAttribute("commentDtoList", commentService.getBySiteId(id));

        return "topo/topo-read";
    }

    @GetMapping("/topo/new")
    public String editTopo( Model model) {

        model.addAttribute("topoDto", new TopoDto());
        return "topo/topo-update";
    }

    @GetMapping("/topo/update/{id}")
    public String updateTopo(@PathVariable("id") Long id, Model model) {
        TopoDto topoDto = (TopoDto) model.getAttribute("topoDto");
        topoService.save(topoDto);

        return "topo/topo-read";
    }

    @GetMapping("/topo/delete/{id}")
    public String deleteTopo(@PathVariable("id") Integer id, Model model) {
        //TODO

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();



        return "topo/topo-list";
    }
}
