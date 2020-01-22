package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Cotation;
import com.dsf.escalade.repository.business.CotationRepository;
import com.dsf.escalade.web.dto.CotationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class CotationServiceImplTest {
    @Autowired
    private CotationRepository cotationRepository;
    @Autowired
    private CotationService cotationService;
    
    private Cotation cotation;
    private CotationDto cotationDto;

    CotationServiceImplTest() {
    }


    @Test
    void Given_Entity_When_AskConvertion_Then_GetRightDto() {

        cotation = new Cotation();

        Integer cotationId = 11;
        String levelFR = "niveau FR";
        String levelGB = "niveau GB";
        String levelUS = "niveau US";
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

    @Test
    void dtoToEntity() {
    }

    @Test
    void findAll() {
    }
}