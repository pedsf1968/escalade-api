package com.dsf.escalade.web.controller;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ResourcesController {

   @Value("${app.images.repository}")
   private String imagesRepository;
   @Value("${app.topo.repository}")
   private String topoRepository;
   @Value("${app.sector.repository}")
   private String sectorRepository;
   @Value("${app.voie.repository}")
   private String voieRepository;
   @Value("${app.avatar.repository}")
   private String avatarRepository;


   @GetMapping("/images/{imageName}")
   public @ResponseBody byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
      InputStream in = new FileInputStream(imagesRepository+imageName);
      return IOUtils.toByteArray(in);
   }

   @GetMapping("/images/topo/{imageName}")
   public @ResponseBody byte[] getTopoImages(@PathVariable("imageName") String imageName) throws IOException {
      InputStream in = new FileInputStream(topoRepository + imageName);
      return IOUtils.toByteArray(in);
   }

   @GetMapping("/images/sector/{imageName}")
   public @ResponseBody byte[] getSectorImages(@PathVariable("imageName") String imageName) throws IOException {
      InputStream in = new FileInputStream(sectorRepository + imageName);
      return IOUtils.toByteArray(in);
   }

   @GetMapping("/images/voie/{imageName}")
   public @ResponseBody byte[] getVoieImages(@PathVariable("imageName") String imageName) throws IOException {
      InputStream in = new FileInputStream(voieRepository + imageName);
      return IOUtils.toByteArray(in);
   }

   @GetMapping("/images/avatar/{imageName}")
   public @ResponseBody byte[] getAvatarImages(@PathVariable("imageName") String imageName) throws IOException {
      InputStream in = new FileInputStream(avatarRepository + imageName);
      return IOUtils.toByteArray(in);
   }


}
