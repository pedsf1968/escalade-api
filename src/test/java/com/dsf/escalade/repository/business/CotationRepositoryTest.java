package com.dsf.escalade.repository.business;


import com.dsf.escalade.model.business.Cotation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.assertNotNull;

@DataJpaTest
public class CotationRepositoryTest {

   @Autowired
   private CotationRepository cotationRepository;

   @Test
   public void findAll() {
      Cotation cotation = new Cotation();
      cotation.setId(22);
      cotation.setLevelFR("fr");
      cotation.setLevelGB("gb");
      cotation.setLevelUS("us");
      cotationRepository.save(cotation);
      assertNotNull(cotationRepository.findAll());
   }
}