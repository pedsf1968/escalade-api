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
}
