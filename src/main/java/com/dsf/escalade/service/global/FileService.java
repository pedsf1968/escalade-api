package com.dsf.escalade.service.global;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {
   void uploadFile(MultipartFile file, String directory);
}
