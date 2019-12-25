package com.dsf.escalade.service.global;

import com.dsf.escalade.web.error.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service("FileService")
public class FileServiceImpl implements FileService {
   @Value("${app.upload.sector:${user.home}}")
   public String uploadDir;

   public void uploadFile(MultipartFile file, String directory) {

      try {
         //Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
         Path copyLocation = Paths.get(directory + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
         log.info("\n\nINFO copylocation :"+copyLocation);
         log.info("\n\nINFO directory :"+directory);
         Files.copy(file.getInputStream(), Paths.get(directory), StandardCopyOption.REPLACE_EXISTING);
      } catch (Exception e) {
         e.printStackTrace();
         throw new FileStorageException("Could not store file " + file.getOriginalFilename()
               + ". Please try again!");
      }
   }
}
