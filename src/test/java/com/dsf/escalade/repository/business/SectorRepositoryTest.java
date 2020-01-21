package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Sector;
import com.dsf.escalade.model.business.SiteType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class SectorRepositoryTest {

   @Autowired
   private SectorRepository sectorRepository;

   @Test
   public void getOne() {
      Sector sector = new Sector();
      sector.setEquipment("equipement");
      sector.setType(SiteType.SECTOR);
      sector.setTopoId(12);
      sector.setLatitude("123");
      sector.setLongitude("456");
      sector.setManagerId(12);
      sector.setMapLink("maplink");
      sector.setPhotoLink("photolink");
      sector.setName("sector");
      sector.setNbComment(5);

      sector = sectorRepository.save(sector);
      Sector sectorSaved = sectorRepository.getOne(sector.getId());

      assertEquals(sectorSaved.getEquipment(),sector.getEquipment());
      assertEquals(sectorSaved.getType(),sector.getType());
      assertEquals(sectorSaved.getTopoId(),sector.getTopoId());
      assertEquals(sectorSaved.getLatitude(),sector.getLatitude());
      assertEquals(sectorSaved.getLongitude(),sector.getLongitude());
      assertEquals(sectorSaved.getManagerId(),sector.getManagerId());
      assertEquals(sectorSaved.getMapLink(),sector.getMapLink());
      assertEquals(sectorSaved.getPhotoLink(),sector.getPhotoLink());
      assertEquals(sectorSaved.getName(),sector.getName());
      assertEquals(sectorSaved.getNbComment(),sector.getNbComment());
   }

   @Test
   public void findByTopoId() {
      Sector sector = new Sector();
      Integer topoId = 12;
      sector.setEquipment("equipement");
      sector.setType(SiteType.SECTOR);
      sector.setTopoId(topoId);
      sector.setLatitude("123");
      sector.setLongitude("456");
      sector.setManagerId(12);
      sector.setMapLink("maplink");
      sector.setPhotoLink("photolink");
      sector.setName("sector");
      sector.setNbComment(5);

      sector = sectorRepository.save(sector);
      assertNotNull(sectorRepository.findByTopoId(topoId));
   }

   @Test
   public void save() {
      Sector sector = new Sector();
      sector.setEquipment("equipement");
      sector.setType(SiteType.SECTOR);
      sector.setTopoId(12);
      sector.setLatitude("123");
      sector.setLongitude("456");
      sector.setManagerId(12);
      sector.setMapLink("maplink");
      sector.setPhotoLink("photolink");
      sector.setName("sector");
      sector.setNbComment(5);

      Sector sectorSaved = sectorRepository.save(sector);

      assertEquals(sectorSaved.getEquipment(),sector.getEquipment());
      assertEquals(sectorSaved.getType(),sector.getType());
      assertEquals(sectorSaved.getTopoId(),sector.getTopoId());
      assertEquals(sectorSaved.getLatitude(),sector.getLatitude());
      assertEquals(sectorSaved.getLongitude(),sector.getLongitude());
      assertEquals(sectorSaved.getManagerId(),sector.getManagerId());
      assertEquals(sectorSaved.getMapLink(),sector.getMapLink());
      assertEquals(sectorSaved.getPhotoLink(),sector.getPhotoLink());
      assertEquals(sectorSaved.getName(),sector.getName());
      assertEquals(sectorSaved.getNbComment(),sector.getNbComment());
   }
}