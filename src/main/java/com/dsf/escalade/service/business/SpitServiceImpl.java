package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.Spit;
import com.dsf.escalade.model.business.SpitPK;
import com.dsf.escalade.repository.business.SpitRepository;
import com.dsf.escalade.web.dto.SpitDto;
import com.dsf.escalade.web.dto.SpitDtoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SpitService")
public class SpitServiceImpl implements SpitService {
   private final SpitRepository spitRepository;

   @Autowired
   public SpitServiceImpl(SpitRepository spitRepository) {
      this.spitRepository = spitRepository;
   }

   @Override
   public SpitDto entityToDto(Spit spit) {
      if(spit==null){
         return null;
      }

      SpitDto spitDto = new SpitDto();

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
   public Spit dtoToEntity(SpitDto spitDto) {
      if(spitDto==null){
         return null;
      }
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

      return spit;
   }

   @Override
   public SpitDtoList findByLongueurId(Integer longueurId) {
      SpitDtoList spitDtoList = new SpitDtoList();

      for(Spit spit : spitRepository.findByLongueurId(longueurId)){
         spitDtoList.addSpitDto(entityToDto(spit));
      }

      return spitDtoList;
   }

   @Override
   public void save(SpitDto spitDto) {
      Spit spit = dtoToEntity(spitDto);

      spitRepository.save(spit);
   }

   @Override
   public void saveAll(SpitDtoList spitDtoList) {
      for(SpitDto spitDto : spitDtoList.getSpitDtos()){
         save(spitDto);
      }
   }

   @Override
   public void delete(SpitDto spitDto) {
      Spit spit = dtoToEntity(spitDto);

      spitRepository.delete(spit);
   }

   @Override
   public Integer getLastSpitNumber(Integer topoId){
      Spit spit = spitRepository.findFirstBySpitPKTopoIdOrderBySpitPKNumberDesc(topoId);

      if(spit!=null){
         return spit.getSpitPK().getNumber();
      }

      return 0;
   }

   @Override
   public SpitDto getOne(Integer topoId, Integer number){
      Spit spit = spitRepository.findBySpitPKTopoIdAndSpitPKNumber(topoId,number);

      return entityToDto(spit);
   }

   @Override
   public Integer getLongueurCotationAverage(Integer topoId, Integer laneId, Integer lengthId) {
      return spitRepository.getLongueurCotationAverage(topoId,laneId, lengthId);
   }

   @Override
   public Integer getVoieCotationAverage(Integer topoId, Integer laneId) {
      return spitRepository.getVoieCotationAverage(topoId, laneId);
   }

   @Override
   public Integer getTopoCotationAverage(Integer topoId) {
      return spitRepository.getTopoCotationAverage(topoId);
   }

   @Override
   public Integer getCotationIdMaxBySpitPKTopoId(Integer topoId) {
      return spitRepository.getCotationIdMaxBySpitPKTopoId(topoId);
   }

   @Override
   public Integer getCotationIdMinBySpitPKTopoId(Integer topoId) {
      return spitRepository.getCotationIdMinBySpitPKTopoId(topoId);
   }
}
