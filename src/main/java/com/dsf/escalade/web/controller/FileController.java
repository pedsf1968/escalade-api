package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.service.global.FileService;
import com.dsf.escalade.web.controller.path.PathTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class FileController {
   private final FileService fileService;
   @Autowired
   private Environment environment;

   public FileController(FileService fileService) {
      this.fileService = fileService;
   }


   @GetMapping("/uploadFile")
   public String uploadFile(Model model){
      return "fileUpload";
   }

   @PostMapping("/uploadFile")
   public String uploadFile(@RequestParam("file") MultipartFile file,
                            @RequestParam("fileType") String fileType,
                            @RequestParam("siteType") String siteType,
                            @RequestParam("id") Integer id,
                            Model model) {

      if (siteType.equals(SiteType.TOPO.toString())){
         fileService.uploadFile(file, environment.getProperty("app.upload.topo")+ "/"  + siteType + id + fileType + ".jpg");
         model.addAttribute("fileName", siteType + id + fileType + ".jpg");
         return PathTable.TOPO_UPDATE_R + id;
      } else if (siteType.equals(SiteType.SECTOR.toString())){
         fileService.uploadFile(file, environment.getProperty("app.upload.sector")+ "/" + siteType + id + fileType + ".jpg");
         model.addAttribute("fileName", siteType + id + fileType + ".jpg");
         return PathTable.SECTOR_UPDATE_R + id;
      } else if (siteType.equals(SiteType.VOIE.toString())){
         fileService.uploadFile(file, environment.getProperty("app.upload.voie")+ "/" +  siteType + id + fileType + ".jpg");
         model.addAttribute("fileName", siteType + id + fileType + ".jpg");
         return PathTable.VOIE_UPDATE_R + id;
      }  else {
         fileService.uploadFile(file, environment.getProperty("app.upload.avatar")+ "/" +  id + fileType + ".jpg");
         model.addAttribute("fileName",  id + fileType + ".jpg");
         return "fileUpload";
      }

   }

}
