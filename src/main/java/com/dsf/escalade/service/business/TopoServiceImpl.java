package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.service.global.UserService;
import com.dsf.escalade.web.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("TopoService")
public class TopoServiceImpl implements TopoService {
   private final TopoRepository topoRepository;
   private final SectorService sectorService;
   private final VoieService voieService;
   private final SpitService spitService;
   private final UserService userService;

   public TopoServiceImpl(TopoRepository topoRepository, @Lazy SectorService sectorService, @Lazy VoieService voieService, @Lazy SpitService spitService, UserService userService) {
      this.topoRepository = topoRepository;
      this.sectorService = sectorService;
      this.voieService = voieService;
      this.spitService = spitService;
      this.userService = userService;
   }

   @Override
   public TopoDto entityToDto(Topo topo) {
      if(topo==null){
         return null;
      }

      TopoDto topoDto = new TopoDto();

      topoDto.setId(topo.getId());
      topoDto.setName(topo.getName());
      topoDto.setAccess(topo.getAccess());
      topoDto.setLongitude(topo.getLongitude());
      topoDto.setLatitude(topo.getLatitude());
      topoDto.setNbComment(topo.getNbComment());
      topoDto.setPhotoLink(topo.getPhotoLink());
      topoDto.setMapLink(topo.getMapLink());
      topoDto.setRegion(topo.getRegion());
      topoDto.setAddressId(topo.getAddressId());
      topoDto.setDate(topo.getDate());
      topoDto.setDescription(topo.getDescription());
      topoDto.setTechnic(topo.getTechnic());
      topoDto.setStatus(topo.getStatus().toString());
      topoDto.setStatusAuto(topo.getStatusAuto());
      topoDto.setCotationMin(topo.getCotationMin());
      topoDto.setCotationMax(topo.getCotationMax());
      topoDto.setNbLane(topo.getNbLane());

      if (topo.getManagerId() != null) {
         topoDto.setAliasManager(userService.getOne(topo.getManagerId()).getAlias());
      }

      if (topo.getClimberId() != null) {
         topoDto.setAliasClimber(userService.getOne(topo.getClimberId()).getAlias());
      }

      return topoDto;

   }

   @Override
   public Topo dtoToEntity(TopoDto topoDto) {
      if(topoDto==null){
         return null;
      }

      Topo topo = new Topo();
      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      topo.setId(topoDto.getId());
      topo.setName(topoDto.getName());
      topo.setLongitude(topoDto.getLongitude());
      topo.setLatitude(topoDto.getLatitude());
      topo.setNbComment(topoDto.getNbComment());
      topo.setType(SiteType.TOPO);
      topo.setPhotoLink(topoDto.getPhotoLink());
      topo.setMapLink(topoDto.getMapLink());
      topo.setRegion(topoDto.getRegion());
      topo.setAddressId(topoDto.getAddressId());
      topo.setDate(topoDto.getDate());
      topo.setDescription(topoDto.getDescription());
      topo.setTechnic(topoDto.getTechnic());
      topo.setAccess(topoDto.getAccess());
      topo.setStatus(StatusType.valueOf(topoDto.getStatus()));
      topo.setStatusAuto(topoDto.getStatusAuto());
      topo.setCotationMin(topoDto.getCotationMin());
      topo.setCotationMax(topoDto.getCotationMax());
      topo.setNbLane(topoDto.getNbLane());


      if (topoDto.getAliasManager() != null && userDto!=null) {
            topo.setManagerId(userDto.getId());
         }


      if (topoDto.getAliasClimber() != null) {
         userDto = userService.findByAlias(topoDto.getAliasClimber());
         if(userDto!=null) {
            topo.setClimberId(userDto.getId());
         }
         topo.setClimberId(null);
      }

      return topo;
   }

   @Override
   public List<TopoDto> findAll() {
      List<TopoDto> topoDtoList = new ArrayList<>();

      for (Topo topo : topoRepository.findAll()) {
         topoDtoList.add(entityToDto(topo));
      }

      return topoDtoList;
   }

   @Override
   public List<TopoDto> findByManagerId(Integer id) {
      List<TopoDto> topoDtoList = new ArrayList<>();

      for (Topo topo : topoRepository.findByManagerId(id)) {
         topoDtoList.add(entityToDto(topo));
      }

      return topoDtoList;
   }

   @Override
   public List<TopoDto> findByClimberId(Integer id) {
      List<TopoDto> topoDtoList = new ArrayList<>();

      for (Topo topo : topoRepository.findByClimberId(id)) {
         topoDtoList.add(entityToDto(topo));
      }

      return topoDtoList;
   }

   @Override
   public TopoDto getOne(Integer id) {
      Topo topo =  topoRepository.getOne(id);

      return entityToDto(topo);
   }

   @Override
   public Integer save(TopoDto topoDto) {
      Topo topo = dtoToEntity(topoDto);

      return topoRepository.save(topo).getId();
   }

   @Override
   public Integer delete(TopoDto topoDto) {
      Integer topoId = topoDto.getId();

      if (topoId != null) {
         topoRepository.delete(topoRepository.getOne(topoId));
         return topoId;
      }

      return null;
   }

   @Override
   public List<String> findAllRegion() {
      return topoRepository.findAllRegion();
   }

   @Override
   public List<String> findAllAlias() {
      return topoRepository.findAllAlias();
   }


   public List<TopoDto> findAllFiltered(TopoDto filter) {

      List<TopoDto> topoDtos = new ArrayList<>();

      List<Topo> topos = topoRepository.findAll(new Specification<Topo>() {

         @Override
         public Predicate toPredicate(Root<Topo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<Predicate> predicates = new ArrayList<>();

            // filter by region
            if ((filter.getRegion() != null) && ! filter.getRegion().equals("0")) {
               log.info("\nFilter region : " + filter.getRegion());
               predicates.add(cb.equal(root.get("region"), filter.getRegion()));
            }

            //filter by manager
            if ((filter.getAliasManager() != null) && ! filter.getAliasManager().equals("0")) {
               log.info("\nFilter alias manager : " + filter.getAliasManager());
               predicates.add(cb.equal(root.get("managerId"), userService.findByAlias(filter.getAliasManager()).getId()) );
            }

            //filter by status
            if ((filter.getStatus() != null) && !filter.getStatus().equals("0")){
               log.info("\nFilter status : " + filter.getStatus());
               predicates.add(cb.equal(root.get("status"),StatusType.valueOf(filter.getStatus())));
            }

            //filter by date greater
            if ((filter.getDate() != null) && ! filter.getDate().equals("0")) {
               log.info("\nFilter date : " + simpleDateFormat.format(filter.getDate()));
               predicates.add(cb.greaterThan(root.get("date"), Date.valueOf(simpleDateFormat.format(filter.getDate()))));
            }

            //filter by cotation maximum
            if ((filter.getCotationMax() != null) && ! filter.getCotationMax().equals("0")) {
               log.info("\nFilter cotation max : " + filter.getCotationMax());
               predicates.add(cb.lessThanOrEqualTo(root.get("cotationMax"), filter.getCotationMax()));
            }

            //filter by lane min
            if ((filter.getNbLane() != null) && ! filter.getNbLane().equals("0")) {
               log.info("\nFilter lane min : " + filter.getNbLane());
               predicates.add(cb.greaterThanOrEqualTo(root.get("nbLane"), filter.getNbLane()));
            }


            return cb.and(predicates.toArray(new Predicate[0]));
         }
      });

      for (Topo topo : topos) {
         topoDtos.add(entityToDto(topo));
      }

      return topoDtos;
   }

   public void updateCotationRange(Integer topoId){
         Topo topo = topoRepository.getOne(topoId);

         topo.setCotationMin(spitService.getCotationIdMinBySpitPKTopoId(topoId));
         topo.setCotationMax(spitService.getCotationIdMaxBySpitPKTopoId(topoId));

      topoRepository.save(topo);
   }

   public Integer increaseLaneCounter(Integer topoId){
      Topo topo = topoRepository.getOne(topoId);
      Integer nbLane = topo.getNbLane();

      if (nbLane == null){
         nbLane = 1;
      }

      topo.setNbLane(++nbLane);
      topoRepository.save(topo);

      return nbLane;
   }

   public Integer decreaseLaneCounter(Integer topoId){
      Topo topo = topoRepository.getOne(topoId);
      Integer nbLane = topo.getNbLane();

      if (nbLane == null){
         nbLane = 1;
      }

      topo.setNbLane(--nbLane);
      topoRepository.save(topo);

      return nbLane;
   }

   @Override
   public Boolean hasRight(TopoDto topoDto){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      UserDto userDto = userService.findByAlias(topoDto.getAliasManager());

      if (userDto.getEmail().equals(authentication.getName())) {
         return Boolean.TRUE;
      }

      return Boolean.FALSE;
   }

   @Override
   public TopoFullDto getFull(Integer topoId) {
      TopoFullDto topofullDto = new TopoFullDto();
      List<SectorDto> sectorDtos = sectorService.findByTopoId(topoId);
      List<SectorFullDto> sectorFullDtos = new ArrayList<>();
      List<VoieDto> voieDtos = voieService.findByParentId(topoId);
      List<VoieFullDto> voieFullDtos = new ArrayList<>();

      for(SectorDto s: sectorDtos) {
         sectorFullDtos.add(sectorService.getFull(s.getId()));
      }

      for (VoieDto v: voieDtos){
         voieFullDtos.add(voieService.getFull(v.getId()));
      }


      topofullDto.setTopo(this.getOne(topoId));
      topofullDto.setSectorList(sectorFullDtos);
      topofullDto.setVoieList(voieFullDtos);

      return topofullDto;
   }

   @Override
   public Integer saveFull(TopoFullDto topoFullDto) {
      Integer oldTopoId = topoFullDto.getTopo().getId();
      Integer topoId = this.save(topoFullDto.getTopo());
      SectorDto sectorDto;
      VoieDto voieDto;

      for(SectorFullDto s: topoFullDto.getSectorList()){
         if(!topoId.equals(oldTopoId)) {
            sectorDto = s.getSector();
            sectorDto.setId(null);
            sectorDto.setTopoId(topoId);
            s.setSector(sectorDto);
         }
         sectorService.saveFull(s);
      }

      for(VoieFullDto v: topoFullDto.getVoieList()){
         if(!topoId.equals(oldTopoId)) {
            voieDto = v.getVoie();
            voieDto.setId(null);
            voieDto.setParentId(topoId);
            v.setVoie(voieDto);
         }
         voieService.saveFull(v);
      }

      return topoId;
   }
}
