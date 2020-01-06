package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Spit;
import com.dsf.escalade.model.business.SpitPK;
import com.dsf.escalade.repository.business.SpitRepository;
import com.dsf.escalade.web.dto.SpitDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SpitService")
public class SpitServiceImpl implements SpitService {
   private final SpitRepository spitRepository;

   public SpitServiceImpl(SpitRepository spitRepository) {
      this.spitRepository = spitRepository;
   }

   @Override
   public SpitDto getOne(SpitPK spitPK) {
      SpitDto spitDto = new SpitDto();
      Spit spit = spitRepository.getOne(spitPK.hashCode());

      spitDto.setTopoId(spit.getSpitPK().getTopoId());
      spitDto.setVoieId(spit.getVoieId());
      spitDto.setLongueurId(spit.getLongueurId());
      spitDto.setNumber(spit.getSpitPK().getNumber());
      spitDto.setCotationId(spit.getCotationId());
      spitDto.setComment(spit.getComment());
      spitDto.setIsRelay(spit.getIsRelay());

      return spitDto;
   }

   @Override
   public List<SpitDto> findByLongueurId(Integer longueurId) {
      List<SpitDto> spitDtos = new ArrayList<>();
      SpitDto spitDto;

      for(Spit spit : spitRepository.findByLongueurId(longueurId)){
         spitDto = new SpitDto();

         spitDto.setTopoId(spit.getSpitPK().getTopoId());
         spitDto.setVoieId(spit.getVoieId());
         spitDto.setLongueurId(spit.getLongueurId());
         spitDto.setNumber(spit.getSpitPK().getNumber());
         spitDto.setCotationId(spit.getCotationId());
         spitDto.setComment(spit.getComment());
         spitDto.setIsRelay(spit.getIsRelay());

         spitDtos.add(spitDto);
      }

      return spitDtos;
   }

   @Override
   public void save(SpitDto spitDto) {
      Spit spit = new Spit();
      SpitPK spitPK = new SpitPK();

      spitPK.setTopoId(spitDto.getTopoId());
      spitPK.setNumber(spitDto.getNumber());
      spit.setSpitPK(spitPK);

      spit.setVoieId(spitDto.getVoieId());
      spit.setLongueurId(spitDto.getLongueurId());
      spit.setCotationId(spitDto.getCotationId());
      spit.setComment(spitDto.getComment());
      spit.setIsRelay(spitDto.getIsRelay());

      spitRepository.save(spit);
   }

   @Override
   public void delete(SpitDto spitDto) {
      Spit spit = new Spit();
      SpitPK spitPK = new SpitPK();

      spitPK.setTopoId(spitDto.getTopoId());
      spitPK.setNumber(spitDto.getNumber());

      spit.setSpitPK(spitPK);

      spit.setVoieId(spitDto.getVoieId());
      spit.setLongueurId(spitDto.getLongueurId());
      spit.setCotationId(spitDto.getCotationId());
      spit.setComment(spitDto.getComment());
      spit.setIsRelay(spitDto.getIsRelay());

      spitRepository.delete(spit);
   }
}
