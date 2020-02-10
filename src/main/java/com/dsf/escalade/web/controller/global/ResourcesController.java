package com.dsf.escalade.web.controller.global;


import com.dsf.escalade.web.controller.path.PathTable;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
      InputStream  in = new FileInputStream(imagesRepository + imageName);

      return IOUtils.toByteArray(in);
   }


   @GetMapping("/images/{type}/{imageName}")
   public @ResponseBody byte[] getBusinessImage(@PathVariable("type") String type, @PathVariable("imageName") String imageName) throws IOException {
      InputStream in;

      if (type.equals("topo")){
         in = new FileInputStream(topoRepository + imageName);
      } else if (type.equals("sector")){
         in = new FileInputStream(sectorRepository + imageName);
      } else if (type.equals("voie")){
         in = new FileInputStream(voieRepository + imageName);
      } else if (type.equals("avatar")){
         in = new FileInputStream(avatarRepository + imageName);
      } else {
         in = new FileInputStream(imagesRepository + imageName);
         if ( in == null){
            in = new FileInputStream(avatarRepository + "avatar.png");
         }
      }
      return IOUtils.toByteArray(in);
   }


   @GetMapping("/help")
   public String help(Model model){
      return PathTable.HELP;
   }

}
