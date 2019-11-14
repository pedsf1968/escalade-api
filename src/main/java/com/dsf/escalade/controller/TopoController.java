package com.dsf.escalade.controller;

import com.dsf.escalade.dao.metier.SiteDao;
import com.dsf.escalade.dao.metier.TopoDao;
import com.dsf.escalade.model.metier.Site;
import com.dsf.escalade.model.metier.Topo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private TopoDao topoDao;

    @GetMapping("/listtopo")
    public String listTopo(Model model) {

        List<Site> listSite = new ArrayList<Site>();
        List<Topo> listTopo;

        listTopo = topoDao.findAll();

        for(Topo topo:listTopo){
            listSite.add(siteDao.getOne(topo.getId()));
        }

       model.addAttribute("siteList", listSite);
        model.addAttribute("topoList", listTopo);

        return "topo/topo-list";
    }

    @GetMapping("/edittopo/{id}")
    public String editTopo(@PathVariable("id") Integer id, Model model) {
        Site site = siteDao.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));

        Topo topo = topoDao.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));

        model.addAttribute("site", site);
        model.addAttribute("topo", site);
        return "topo/topo-update";
    }

    @GetMapping("/deletetopo/{id}")
    public String deleteTopo(@PathVariable("id") Integer id, Model model) {
        Topo topo = topoDao.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid topo Id:" + id));
        topoDao.delete(topo);

        Site site = siteDao.findById(id)
              .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));
        siteDao.delete(site);
        listTopo( model);

        List<Site> listSite = new ArrayList<Site>();
        List<Topo> listTopo;

        listTopo = topoDao.findAll();

        for( Topo t:listTopo){
            listSite.add(siteDao.getOne(t.getId()));
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
