package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.repository.business.SiteRepository;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.service.TopoService;
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
    private  final UserRepository userRepository;

    private TopoDto topoDto;
    private TopoService topoService;

    @Autowired
    public TopoController(SiteRepository siteRepository, TopoRepository topoRepository, UserRepository userRepository) {
        this.siteRepository = siteRepository;
        this.topoRepository = topoRepository;
       this.userRepository = userRepository;
    }

    @GetMapping("/topo/list")
    public String listTopo(Model model) {
        List<TopoDto> listTopoDto = new ArrayList<TopoDto>();
        List<Topo> listTopo = topoRepository.findAll();

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentPrincipalName = authentication.getName();

        for(Topo topo:listTopo){
            topoDto = new TopoDto(siteRepository.getOne(topo.getId()),topo);
            topoDto.setAlias(userRepository.getOne(topo.getManager()).getAlias());
            listTopoDto.add(topoDto);
        }

        model.addAttribute("topoDtoList", listTopoDto);

        return "topo/topo-list";
    }

    @GetMapping("/topo/read/{id}")
    public String readTopo(@PathVariable("id") Long id, Model model) {
        Site site = siteRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));

        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        topoDto = new TopoDto(site,topo);
        model.addAttribute("topoDto", topoDto);
        return "topo/topo-read";
    }

    @GetMapping("/topo/create/{id}")
    public String editTopo(@PathVariable("id") Long id, Model model) {
        Site site = siteRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));

        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        topoDto = new TopoDto(site,topo);
        model.addAttribute("topoDto", topoDto);
        return "topo/topo-update";
    }

    @GetMapping("/topo/update/{id}")
    public String updateTopo(@PathVariable("id") Long id, Model model) {
        TopoDto topoDto = (TopoDto) model.getAttribute("topoDto");
        topoService.save(topoDto);

        return "topo/topo-read";
    }

    @GetMapping("/topo/delete/{id}")
    public String deleteTopo(@PathVariable("id") Long id, Model model) {
        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));
        topoRepository.delete(topo);

        Site site = siteRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));
        siteRepository.delete(site);


        List<TopoDto> listTopoDto = new ArrayList<TopoDto>();
        List<Topo> listTopo = topoRepository.findAll();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        for(Topo t:listTopo){
            topoDto = new TopoDto(siteRepository.getOne(t.getId()),topo);
            topoDto.setAlias(userRepository.getOne(t.getManager()).getAlias());

            listTopoDto.add(topoDto);
        }


        model.addAttribute("topoList", listTopoDto);


        return "topo/topo-list";
    }
}
