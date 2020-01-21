package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Cotation;
import com.dsf.escalade.repository.business.CotationRepository;
import com.dsf.escalade.web.dto.CotationDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CotationServiceImplTest {

   @Mock
   CotationRepository cotationRepository;
   @InjectMocks
   CotationService cotationService;

   @Test
   public void GIVEN_entity_WHEN_askDto_THEN_getRightDto() {
      Cotation cotation = new Cotation();
      CotationDto cotationDto = new CotationDto();
      Integer cotationId = 11;
      String levelFR = "fr";
      String levelGB = "gb";
      String levelUS = "us";

      cotation.setId(cotationId);
      cotation.setLevelFR(levelFR);
      cotation.setLevelGB(levelGB);
      cotation.setLevelUS(levelUS);

      cotationDto = cotationService.entityToDto(cotation);

      assertEquals(cotationId,cotationDto.getId());
      assertEquals(levelFR,cotationDto.getLevelFR());
      assertEquals(levelGB,cotationDto.getLevelGB());
      assertEquals(levelUS,cotationDto.getLevelUS());

   }
}