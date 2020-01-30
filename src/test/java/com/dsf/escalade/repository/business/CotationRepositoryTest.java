package com.dsf.escalade.repository.business;


import com.dsf.escalade.model.business.Cotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CotationRepositoryTest {

   @Autowired
   private CotationRepository cotationRepository;

   @Test
   public void findAll() throws Exception {
      Cotation cotation = new Cotation();
      cotation.setId(22);
      cotation.setLevelFR("fr");
      cotation.setLevelGB("gb");
      cotation.setLevelUS("us");
      cotationRepository.save(cotation);
      assertNotNull(cotationRepository.findAll());
   }
}