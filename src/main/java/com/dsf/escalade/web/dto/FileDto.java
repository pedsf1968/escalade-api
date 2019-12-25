package com.dsf.escalade.web.dto;

import com.dsf.escalade.model.business.SiteType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileDto {
   private Integer id;
   private SiteType siteType;
   private MultipartFile[] datas;
}
