package com.dsf.escalade.web.controller;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.service.business.SectorService;
import com.dsf.escalade.service.business.TopoService;
import com.dsf.escalade.service.business.VoieService;
import com.dsf.escalade.service.global.FileService;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.controller.path.PathTable;
import com.dsf.escalade.web.dto.SectorDto;
import com.dsf.escalade.web.dto.TopoDto;
import com.dsf.escalade.web.dto.VoieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
   private final UserService userService;
   private final TopoService topoService;
   private final SectorService sectorService;
   private final VoieService voieService;


   @Value("${app.topo.repository}")
   private String topoRepository;
   @Value("${app.sector.repository}")
   private String sectorRepository;
   @Value("${app.voie.repository}")
   private String voieRepository;
   @Value("${app.avatar.repository}")
   private String avatarRepository;

   public FileController(FileService fileService, UserService userService, TopoService topoService, SectorService sectorService, VoieService voieService) {
      this.fileService = fileService;
      this.userService = userService;
      this.topoService = topoService;
      this.sectorService = sectorService;
      this.voieService = voieService;
   }

   @GetMapping("/uploadFile")
   public String uploadFile(Model model){
      return "fileUpload";
   }

   @PostMapping("/uploadFile")
   public String uploadFile(@RequestParam("file") MultipartFile file,
                            @RequestParam("fileType") String fileType,
                            @RequestParam("siteType") String siteType,
                            @RequestParam("siteId") Integer id,
                            Model model) {

      if (siteType.equals(SiteType.TOPO.toString())){
         fileService.uploadFile(file, topoRepository+ siteType + id + fileType + ".jpg");
         TopoDto topoDto = topoService.getOne(id);
         if(fileType.equals("photo")){
            topoDto.setPhotoLink("TOPO"+id+"photo.jpg");
         } else {
            topoDto.setMapLink("TOPO"+id+"map.jpg");
         }
         topoService.save(topoDto);
         model.addAttribute("topoDto",topoDto);
         return PathTable.TOPO_UPDATE_R + id;
      } else if (siteType.equals(SiteType.SECTOR.toString())){
         fileService.uploadFile(file, sectorRepository + siteType + id + fileType + ".jpg");
         SectorDto sectorDto = sectorService.getOne(id);
         if(fileType.equals("photo")){
            sectorDto.setPhotoLink("SECTOR"+id+"photo.jpg");
         } else {
            sectorDto.setMapLink("SECTOR"+id+"map.jpg");
         }
         sectorService.save(sectorDto);
         model.addAttribute("sectorDto",sectorDto);
         return PathTable.SECTOR_UPDATE_R + id;
      } else if (siteType.equals(SiteType.VOIE.toString())){
         fileService.uploadFile(file, voieRepository +  siteType + id + fileType + ".jpg");
         VoieDto voieDto = voieService.getOne(id);
         if(fileType.equals("photo")){
            voieDto.setPhotoLink("VOIE"+id+"photo.jpg");
         } else {
            voieDto.setMapLink("VOIE"+id+"map.jpg");
         }
         voieService.save(voieDto);
         model.addAttribute("voieDto",voieDto);
         return PathTable.VOIE_UPDATE_R + id;
      }  else {
         String fileName = userService.getOne(id).getAlias() + ".jpg";
         fileService.uploadFile(file, avatarRepository + fileName);
         model.addAttribute("fileName",  fileName);
         return PathTable.USER_UPDATE_R;
      }

   }

}
