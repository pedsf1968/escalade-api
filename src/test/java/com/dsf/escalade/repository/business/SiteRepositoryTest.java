package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Site;
import com.dsf.escalade.model.business.SiteType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SiteRepositoryTest {

   @Autowired
   private SiteRepository siteRepository;

   @Test
   public void getOne() {
      Site site = new Site();
      site.setType(SiteType.TOPO);
      site.setLatitude("123");
      site.setLongitude("456");
      site.setManagerId(12);
      site.setMapLink("maplink");
      site.setPhotoLink("photolink");
      site.setName("site");
      site.setNbComment(5);

      site = siteRepository.save(site);
      Site siteSaved = siteRepository.getOne(site.getId());

      assertEquals(siteSaved.getType(),site.getType());
      assertEquals(siteSaved.getLatitude(),site.getLatitude());
      assertEquals(siteSaved.getLongitude(),site.getLongitude());
      assertEquals(siteSaved.getManagerId(),site.getManagerId());
      assertEquals(siteSaved.getMapLink(),site.getMapLink());
      assertEquals(siteSaved.getPhotoLink(),site.getPhotoLink());
      assertEquals(siteSaved.getName(),site.getName());
      assertEquals(siteSaved.getNbComment(),site.getNbComment());
   }

   @Test
   public void getType() {
      Site site = new Site();
      SiteType siteType = SiteType.TOPO;
      site.setType(siteType);
      site.setLatitude("123");
      site.setLongitude("456");
      site.setManagerId(12);
      site.setMapLink("maplink");
      site.setPhotoLink("photolink");
      site.setName("site");
      site.setNbComment(5);

      site = siteRepository.save(site);
      assertEquals(siteRepository.getType(site.getId()),siteType);

   }
}