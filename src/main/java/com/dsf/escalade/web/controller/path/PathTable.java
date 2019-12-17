package com.dsf.escalade.web.controller.path;

public interface PathTable {
   public final static String TOPO_ALL = "topo/topo-all";
   public final static String TOPO_MYLIST = "topo/topo-mylist";
   public final static String TOPO_MYLIST_R = "redirect:/topo/mylist";
   public final static String TOPO_ADD = "topo/topo-add";
   public final static String TOPO_READ = "topo/topo-read";
   public final static String TOPO_READ_R = "redirect:/topo/read/";
   public final static String TOPO_UPDATE = "topo/topo-update";
   public final static String TOPO_UPDATE_R = "redirect:/topo/edit/";

   public final static String SECTOR_ADD = "sector/sector-add";
   public final static String SECTOR_UPDATE = "sector/sector-update";

   public final static String ATTRIBUTE_TOPO = "topoDto";
   public final static String ATTRIBUTE_TOPO_LIST = "topoDtoList";
   public final static String ATTRIBUTE_SECTOR = "sectorDto";
   public final static String ATTRIBUTE_SECTOR_LIST = "sectorDtoList";

   public final static String ATTRIBUTE_ADDRESS = "addressDto";
   public final static String ATTRIBUTE_STATUS_LIST = "statusList";
   public final static String ATTRIBUTE_COMMENT_LIST = "commentDtoList";
   public final static String ATTRIBUTE_TAGS = "tagsByTopoId";

}
