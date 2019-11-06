package com.dsf.escalade.controller;

import com.dsf.escalade.dao.metier.SiteDao;
import com.dsf.escalade.model.metier.Site;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class SiteController {

   @Autowired
   private SiteDao siteDao;


   @GetMapping("/listsite")
   public String listSite(Model model) {

      model.addAttribute("siteList", siteDao.findAll());
      return "site-list";
   }


   @PostMapping("/addsite")
   public String addSite(@Valid Site site, BindingResult result, Model model){
      if (result.hasErrors()){
         return "site-add";
      }

      siteDao.save(site);
      model.addAttribute("siteList",siteDao.findAll());
      return "site-list";
   }

   @GetMapping("/editsite/{id}")
   public String editSite(@PathVariable("id") Integer id, Model model) {
      Site site = siteDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));

      model.addAttribute("site", site);
      return "site-update";
   }

   @PostMapping("/updatesite/{id}")
   public String updateSite(@PathVariable("id") Integer id, @Valid Site site, BindingResult result, Model model) {

      if (result.hasErrors()){

         site.setId(id);
         return "site-update";
      }

      siteDao.save(site);

      model.addAttribute("siteList",siteDao.findAll());
      return "site-list";
   }

   @GetMapping("/deletesite/{id}")
   public String deleteSite(@PathVariable("id") Integer id, Model model) {
      Site site = siteDao.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid site Id:" + id));
      siteDao.delete(site);
      model.addAttribute("users", siteDao.findAll());
      return "site-list";
   }

}


