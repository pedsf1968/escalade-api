package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.model.global.Comment;
import com.dsf.escalade.repository.business.SiteRepository;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.repository.global.AddressRepository;
import com.dsf.escalade.repository.global.CommentRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.service.TopoService;
import com.dsf.escalade.web.dto.AddressDto;
import com.dsf.escalade.web.dto.CommentDto;
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
import java.util.ArrayList;
import java.util.List;


@Controller
@Slf4j
public class TopoController {

    @PersistenceContext
    EntityManager entityManager;

    private final SiteRepository siteRepository;
    private final TopoRepository topoRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CommentRepository commentRepository;

    private TopoService topoService;

    @Autowired
    public TopoController(SiteRepository siteRepository, TopoRepository topoRepository, UserRepository userRepository, AddressRepository addressRepository, CommentRepository commentRepository) {
        this.siteRepository = siteRepository;
        this.topoRepository = topoRepository;
       this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/topo/list")
    public String listTopo(Model model) {
        TopoDto topoDto;

        List<TopoDto> topoDtoList = new ArrayList<TopoDto>();
        List<Topo> listTopo = topoRepository.findAll();

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentPrincipalName = authentication.getName();

        for(Topo topo:listTopo){
            topoDto = new TopoDto(topo);
            topoDto.setAlias(userRepository.getOne(topo.getManager()).getAlias());
            topoDtoList.add(topoDto);
        }

        model.addAttribute("topoDtoList", topoDtoList);

        return "topo/topo-list";
    }

    @GetMapping("/topo/read/{id}")
    public String readTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto;
        List<CommentDto> commentDtoList = new ArrayList<CommentDto>();

        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        // initialize topo DTO with topo object
        topoDto = new TopoDto(topo);
        // Get the alias of the manager
        topoDto.setAlias(userRepository.getOne(topo.getManager()).getAlias());

        // initialize address DTO
        if(topo.getAddress()!=null) {
            AddressDto addressDto = new AddressDto(addressRepository.getOne(topo.getAddress()));
            model.addAttribute("addressDto", addressDto);
        }

        // we get all Topo's comments
        for(Comment comment:commentRepository.findAll()){
            CommentDto commentDto = new CommentDto(comment);
            commentDto.setAlias(userRepository.getOne(comment.getUser_id()).getAlias());
            commentDtoList.add(commentDto);
        }

        model.addAttribute("topoDto", topoDto);
        model.addAttribute("commentDtoList", commentDtoList);
        return "topo/topo-read";
    }

    @GetMapping("/topo/create/{id}")
    public String editTopo(@PathVariable("id") Integer id, Model model) {
        TopoDto topoDto;

        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        model.addAttribute("topoDto", new TopoDto(topo));
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
        TopoDto topoDto;
        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));
        topoRepository.delete(topo);

        List<TopoDto> listTopoDto = new ArrayList<TopoDto>();
        List<Topo> listTopo = topoRepository.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        for(Topo t:listTopo){
            topoDto = new TopoDto(topo);
            topoDto.setAlias(userRepository.getOne(t.getManager()).getAlias());
            listTopoDto.add(topoDto);
        }


        model.addAttribute("topoList", listTopoDto);


        return "topo/topo-list";
    }
}
