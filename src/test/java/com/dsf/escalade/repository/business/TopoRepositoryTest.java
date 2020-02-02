package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.model.global.User;
import com.dsf.escalade.repository.global.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class TopoRepositoryTest {

   @Autowired
   private TopoRepository topoRepository;

   @Autowired
   private UserRepository userRepository;

   @Test
   public void getOne() {
      Topo topo = new Topo();
      topo.setType(SiteType.TOPO);
      topo.setLatitude("123");
      topo.setLongitude("456");
      topo.setManagerId(12);
      topo.setMapLink("maplink");
      topo.setPhotoLink("photolink");
      topo.setName("topo");
      topo.setNbComment(5);
      topo.setNbLane(4);
      topo.setCotationMin(3);
      topo.setCotationMax(13);
      topo.setDate(Date.valueOf("2020-02-13"));
      topo.setStatus(StatusType.REQUESTED);
      topo.setManagerId(12);
      topo.setRegion("Auvergne");
      topo.setAccess("par l'autoroute A1");
      topo.setAddressId(2);
      topo.setClimberId(5);
      topo.setDescription("haute falaise bleu");
      topo.setTechnic("100m de corde");

      topo = topoRepository.save(topo);
      Topo topoSaved = topoRepository.getOne(topo.getId());

      assertEquals(topoSaved.getType(),topo.getType());
      assertEquals(topoSaved.getLatitude(),topo.getLatitude());
      assertEquals(topoSaved.getLongitude(),topo.getLongitude());
      assertEquals(topoSaved.getManagerId(),topo.getManagerId());
      assertEquals(topoSaved.getMapLink(),topo.getMapLink());
      assertEquals(topoSaved.getPhotoLink(),topo.getPhotoLink());
      assertEquals(topoSaved.getName(),topo.getName());
      assertEquals(topoSaved.getNbComment(),topo.getNbComment());
      assertEquals(topoSaved.getNbLane(),topo.getNbLane());
      assertEquals(topoSaved.getCotationMin(),topo.getCotationMin());
      assertEquals(topoSaved.getCotationMin(),topo.getCotationMin());
      assertEquals(topoSaved.getDate(),topo.getDate());
      assertEquals(topoSaved.getStatus(),topo.getStatus());
      assertEquals(topoSaved.getManagerId(),topo.getManagerId());
      assertEquals(topoSaved.getRegion(),topo.getRegion());
      assertEquals(topoSaved.getAccess(),topo.getAccess());
      assertEquals(topoSaved.getAddressId(),topo.getAddressId());
      assertEquals(topoSaved.getClimberId(),topo.getClimberId());
      assertEquals(topoSaved.getDescription(),topo.getDescription());
      assertEquals(topoSaved.getTechnic(),topo.getTechnic());
   }

   @Test
   public void findByManagerId() {
      Topo topo = new Topo();
      topo.setType(SiteType.TOPO);
      topo.setLatitude("123");
      topo.setLongitude("456");
      topo.setManagerId(12);
      topo.setMapLink("maplink");
      topo.setPhotoLink("photolink");
      topo.setName("topo");
      topo.setNbComment(5);
      topo.setNbLane(4);
      topo.setCotationMin(3);
      topo.setCotationMax(13);
      topo.setDate(Date.valueOf("2020-02-13"));
      topo.setStatus(StatusType.REQUESTED);
      Integer managerId = 15;
      topo.setManagerId(managerId);
      topo.setRegion("Auvergne");
      topo.setAccess("par l'autoroute A1");
      topo.setAddressId(2);
      topo.setClimberId(5);
      topo.setDescription("haute falaise bleu");
      topo.setTechnic("100m de corde");

      topo = topoRepository.save(topo);
      List<Topo> topoSaveds = topoRepository.findByManagerId(managerId);

      for(Topo topoSaved:topoSaveds) {
         assertEquals(topoSaved.getManagerId(), topo.getManagerId());
         if(topoSaved.getId()==topo.getId()) {
            assertEquals(topoSaved.getType(), topo.getType());
            assertEquals(topoSaved.getLatitude(), topo.getLatitude());
            assertEquals(topoSaved.getLongitude(), topo.getLongitude());
            assertEquals(topoSaved.getMapLink(), topo.getMapLink());
            assertEquals(topoSaved.getPhotoLink(), topo.getPhotoLink());
            assertEquals(topoSaved.getName(), topo.getName());
            assertEquals(topoSaved.getNbComment(), topo.getNbComment());
            assertEquals(topoSaved.getNbLane(), topo.getNbLane());
            assertEquals(topoSaved.getCotationMin(), topo.getCotationMin());
            assertEquals(topoSaved.getCotationMin(), topo.getCotationMin());
            assertEquals(topoSaved.getDate(),topo.getDate());
            assertEquals(topoSaved.getStatus(), topo.getStatus());
            assertEquals(topoSaved.getManagerId(), topo.getManagerId());
            assertEquals(topoSaved.getRegion(), topo.getRegion());
            assertEquals(topoSaved.getAccess(), topo.getAccess());
            assertEquals(topoSaved.getAddressId(), topo.getAddressId());
            assertEquals(topoSaved.getClimberId(), topo.getClimberId());
            assertEquals(topoSaved.getDescription(), topo.getDescription());
            assertEquals(topoSaved.getTechnic(), topo.getTechnic());
         }
      }
   }

   @Test
   public void findByClimberId() {
      Topo topo = new Topo();
      topo.setType(SiteType.TOPO);
      topo.setLatitude("123");
      topo.setLongitude("456");
      topo.setManagerId(12);
      topo.setMapLink("maplink");
      topo.setPhotoLink("photolink");
      topo.setName("topo");
      topo.setNbComment(5);
      topo.setNbLane(4);
      topo.setCotationMin(3);
      topo.setCotationMax(13);
      topo.setDate(Date.valueOf("2020-02-13"));
      topo.setStatus(StatusType.REQUESTED);
      topo.setManagerId(15);
      topo.setRegion("Auvergne");
      topo.setAccess("par l'autoroute A1");
      topo.setAddressId(2);
      Integer climberId = 5;
      topo.setClimberId(climberId);
      topo.setDescription("haute falaise bleu");
      topo.setTechnic("100m de corde");

      topo = topoRepository.save(topo);
      List<Topo> topoSaveds = topoRepository.findByClimberId(climberId);

      for(Topo topoSaved:topoSaveds) {
         assertEquals(topoSaved.getManagerId(), topo.getManagerId());
         if(topoSaved.getId()==topo.getId()) {
            assertEquals(topoSaved.getType(), topo.getType());
            assertEquals(topoSaved.getLatitude(), topo.getLatitude());
            assertEquals(topoSaved.getLongitude(), topo.getLongitude());
            assertEquals(topoSaved.getMapLink(), topo.getMapLink());
            assertEquals(topoSaved.getPhotoLink(), topo.getPhotoLink());
            assertEquals(topoSaved.getName(), topo.getName());
            assertEquals(topoSaved.getNbComment(), topo.getNbComment());
            assertEquals(topoSaved.getNbLane(), topo.getNbLane());
            assertEquals(topoSaved.getCotationMin(), topo.getCotationMin());
            assertEquals(topoSaved.getCotationMin(), topo.getCotationMin());
            assertEquals(topoSaved.getDate(),topo.getDate());
            assertEquals(topoSaved.getStatus(), topo.getStatus());
            assertEquals(topoSaved.getManagerId(), topo.getManagerId());
            assertEquals(topoSaved.getRegion(), topo.getRegion());
            assertEquals(topoSaved.getAccess(), topo.getAccess());
            assertEquals(topoSaved.getAddressId(), topo.getAddressId());
            assertEquals(topoSaved.getClimberId(), topo.getClimberId());
            assertEquals(topoSaved.getDescription(), topo.getDescription());
            assertEquals(topoSaved.getTechnic(), topo.getTechnic());
         }
      }
   }


   @Test
   public void findAllRegion() {
      List<String> regions = topoRepository.findAllRegion();
      Integer nbRegions = regions.size();
      String regionName = "Auvergne";

      Topo topo = new Topo();
      topo.setType(SiteType.TOPO);
      topo.setLatitude("123");
      topo.setLongitude("456");
      topo.setManagerId(12);
      topo.setMapLink("maplink");
      topo.setPhotoLink("photolink");
      topo.setName("topo");
      topo.setNbComment(5);
      topo.setNbLane(4);
      topo.setCotationMin(3);
      topo.setCotationMax(13);
      topo.setDate(Date.valueOf("2020-02-13"));
      topo.setStatus(StatusType.REQUESTED);
      topo.setManagerId(12);
      topo.setRegion(regionName);
      topo.setAccess("par l'autoroute A1");
      topo.setAddressId(2);
      topo.setClimberId(5);
      topo.setDescription("haute falaise bleu");
      topo.setTechnic("100m de corde");

      topo = topoRepository.save(topo);
      regions = topoRepository.findAllRegion();

      assertEquals(regions.contains(regionName),Boolean.TRUE);
      assertEquals(regions.size(),nbRegions+1);
   }

   @Test
   public void findAllAlias() {
      List<String> aliases = topoRepository.findAllAlias();
      Integer nbAlias = aliases.size();

      // create new user with new alias
      User user = new User();
      String aliasName = "AliasAlias";
      user.setFirstName("Marcel");
      user.setAddressId(2);
      user.setAlias(aliasName);
      user.setEmail("marcel.marcel@hotmail.com");
      user.setPassword("lkjlkjhmkjhmlkh");
      user.setLastName("Marcel");
      user = userRepository.save(user);

      // create a new topo with new user owner
      Topo topo = new Topo();
      topo.setType(SiteType.TOPO);
      topo.setLatitude("123");
      topo.setLongitude("456");
      topo.setManagerId(12);
      topo.setMapLink("maplink");
      topo.setPhotoLink("photolink");
      topo.setName("topo");
      topo.setNbComment(5);
      topo.setNbLane(4);
      topo.setCotationMin(3);
      topo.setCotationMax(13);
      topo.setDate(Date.valueOf("2020-02-13"));
      topo.setStatus(StatusType.REQUESTED);
      topo.setManagerId(user.getId());
      topo.setRegion("regionName");
      topo.setAccess("par l'autoroute A1");
      topo.setAddressId(2);
      topo.setClimberId(5);
      topo.setDescription("haute falaise bleu");
      topo.setTechnic("100m de corde");

      // save topo
      topo = topoRepository.save(topo);

      aliases = topoRepository.findAllAlias();

      assertEquals(aliases.contains(aliasName),Boolean.TRUE);
      assertEquals(aliases.size(),nbAlias+1);

   }
}