package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.repository.business.SiteRepository;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.repository.global.UserRepository;
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

    @Autowired
    public TopoController(SiteRepository siteRepository, TopoRepository topoRepository, UserRepository userRepository) {
        this.siteRepository = siteRepository;
        this.topoRepository = topoRepository;
       this.userRepository = userRepository;
    }

    @GetMapping("/listtopos")
    public String listTopo(Model model) {


        List<TopoDto> listTopoDto = new ArrayList<TopoDto>();
        List<Topo> listTopo;

        listTopo = topoRepository.findAll();

       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       String currentPrincipalName = authentication.getName();

        for(Topo topo:listTopo){
            TopoDto topoDto = new TopoDto(siteRepository.getOne(topo.getId()),topo);
            topoDto.setPseudo(userRepository.getOne(topo.getManager()).getAlias());

            listTopoDto.add(topoDto);
        }


        model.addAttribute("topoList", listTopoDto);

        return "topo/topo-list";
    }

    @GetMapping("/viewtopo/{id}")
    public String viewTopo(@PathVariable("id") Integer id, Model model) {
        Site site = siteRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));

        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        model.addAttribute("site", site);
        model.addAttribute("topo", topo);
        return "topo/topo-view";
    }

    @GetMapping("/edittopo/{id}")
    public String editTopo(@PathVariable("id") Integer id, Model model) {
        Site site = siteRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));

        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        model.addAttribute("site", site);
        model.addAttribute("topo", topo);
        return "topo/topo-update";
    }

    @GetMapping("/deletetopo/{id}")
    public String deleteTopo(@PathVariable("id") Integer id, Model model) {
        Topo topo = topoRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));
        topoRepository.delete(topo);

        Site site = siteRepository.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));
        siteRepository.delete(site);
        listTopo( model);

        List<Site> listSite = new ArrayList<Site>();
        List<Topo> listTopo;

        listTopo = topoRepository.findAll();

        for( Topo t:listTopo){
            listSite.add(siteRepository.getOne(t.getId()));
            log.info("\n\n INFO topo :"+ t.toString());
        }

        for (Site s:listSite){
            log.info("\n\n INFO site :"+ s.toString());
        }

        model.addAttribute("siteList", listSite);
        model.addAttribute("topoList", listTopo);

        return "topo/topo-list";
    }
}
