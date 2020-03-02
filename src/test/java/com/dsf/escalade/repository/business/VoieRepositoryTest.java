package com.dsf.escalade.repository.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.Voie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class VoieRepositoryTest {
    @Autowired
    private VoieRepository voieRepository;

    @Test
    public void getOne() {
        Voie voie = new Voie();
        voie.setType(SiteType.VOIE);
        voie.setLatitude("123");
        voie.setLongitude("456");
        voie.setManagerId(12);
        voie.setMapLink("maplink");
        voie.setPhotoLink("photolink");
        voie.setName("site");
        voie.setNbComment(5);
        voie.setCotationId(15);
        voie.setHeigth(121);
        voie.setIsEquipped(Boolean.TRUE);
        voie.setParentId(3);

        voie = voieRepository.save(voie);
        Voie voieSaved = voieRepository.getOne(voie.getId());

        assertEquals(voieSaved.getType(),voie.getType());
        assertEquals(voieSaved.getLatitude(),voie.getLatitude());
        assertEquals(voieSaved.getLongitude(),voie.getLongitude());
        assertEquals(voieSaved.getManagerId(),voie.getManagerId());
        assertEquals(voieSaved.getMapLink(),voie.getMapLink());
        assertEquals(voieSaved.getPhotoLink(),voie.getPhotoLink());
        assertEquals(voieSaved.getName(),voie.getName());
        assertEquals(voieSaved.getNbComment(),voie.getNbComment());
        assertEquals(voie.getCotationId(),voie.getCotationId());
        assertEquals(voie.getHeigth(),voie.getHeigth());
        assertEquals(voie.getIsEquipped(),voie.getIsEquipped());
        assertEquals(voie.getParentId(),voie.getParentId());
    }

    @Test
    public void findByParentId() {
        Voie voie = new Voie();
        voie.setType(SiteType.VOIE);
        voie.setLatitude("123");
        voie.setLongitude("456");
        voie.setManagerId(12);
        voie.setMapLink("maplink");
        voie.setPhotoLink("photolink");
        voie.setName("site");
        voie.setNbComment(5);
        voie.setCotationId(15);
        voie.setHeigth(121);
        voie.setIsEquipped(Boolean.TRUE);
        Integer parentId = 3;
        voie.setParentId(parentId);

        voie = voieRepository.save(voie);
        List<Voie> voieSaveds = voieRepository.findByParentId(parentId);

        for(Voie voieSaved:voieSaveds) {
            if( voieSaved.getId()==voie.getId()) {
                assertEquals(voieSaved.getType(), voie.getType());
                assertEquals(voieSaved.getLatitude(), voie.getLatitude());
                assertEquals(voieSaved.getLongitude(), voie.getLongitude());
                assertEquals(voieSaved.getManagerId(), voie.getManagerId());
                assertEquals(voieSaved.getMapLink(), voie.getMapLink());
                assertEquals(voieSaved.getPhotoLink(), voie.getPhotoLink());
                assertEquals(voieSaved.getName(), voie.getName());
                assertEquals(voieSaved.getNbComment(), voie.getNbComment());
                assertEquals(voie.getCotationId(), voie.getCotationId());
                assertEquals(voie.getHeigth(), voie.getHeigth());
                assertEquals(voie.getIsEquipped(), voie.getIsEquipped());
                assertEquals(voie.getParentId(), voie.getParentId());
            }
        }
    }
}