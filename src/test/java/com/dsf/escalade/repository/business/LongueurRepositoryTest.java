package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.Longueur;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class LongueurRepositoryTest {

   @Autowired
   private LongueurRepository longueurRepository;

   @Test
   public void save(){
      Longueur longueur = new Longueur();
      longueur.setCotationId(11);
      longueur.setHeigth(25);
      longueur.setName("Cotation name");
      longueur.setVoieId(4);
      Longueur longueurSaved = longueurRepository.save(longueur);

      assertEquals(longueurSaved.getCotationId(),longueur.getCotationId());
      assertEquals(longueurSaved.getHeigth(),longueur.getHeigth());
      assertEquals(longueurSaved.getName(),longueur.getName());
      assertEquals(longueurSaved.getVoieId(),longueur.getVoieId());
   }

   @Test
   public void getOne() {
      Longueur longueur = new Longueur();
      Integer longueurId;
      longueur.setCotationId(11);
      longueur.setHeigth(25);
      longueur.setName("Cotation name");
      longueur.setVoieId(4);

      longueur = longueurRepository.save(longueur);
      Longueur longueurGet = longueurRepository.getOne(longueur.getId());
      assertEquals(longueurGet.getId(),longueur.getId());
      assertEquals(longueurGet.getCotationId(),longueur.getCotationId());
      assertEquals(longueurGet.getHeigth(),longueur.getHeigth());
      assertEquals(longueurGet.getName(),longueur.getName());
      assertEquals(longueurGet.getVoieId(),longueur.getVoieId());
   }

   @Test
   public void delete() {
      Longueur longueur = new Longueur();
      longueur.setCotationId(11);
      longueur.setHeigth(25);
      longueur.setName("Cotation name");
      longueur.setVoieId(4);

      longueur = longueurRepository.save(longueur);
      Integer longueurId = longueur.getId();

      longueurRepository.delete(longueur);
      assertThrows(JpaObjectRetrievalFailureException.class, ()->{ longueurRepository.getOne(longueurId);});
   }

   @Test
   public void findByVoieId() {
      Longueur longueur = new Longueur();
      Integer voieId = 4;
      longueur.setCotationId(11);
      longueur.setHeigth(25);
      longueur.setName("Cotation name");
      longueur.setVoieId(voieId);

      longueur = longueurRepository.save(longueur);
      assertNotNull(longueurRepository.findByVoieId(voieId));

   }
}