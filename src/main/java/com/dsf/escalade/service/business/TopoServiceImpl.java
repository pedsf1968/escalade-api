package com.dsf.escalade.service.business;

import com.dsf.escalade.model.business.SiteType;
import com.dsf.escalade.model.business.StatusType;
import com.dsf.escalade.model.business.Topo;
import com.dsf.escalade.repository.business.TopoRepository;
import com.dsf.escalade.repository.global.UserRepository;
import com.dsf.escalade.web.dto.TopoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
   private final UserRepository userRepository;

   @Autowired
   public TopoServiceImpl(TopoRepository topoRepository, UserRepository userRepository) {
      this.topoRepository = topoRepository;
      this.userRepository = userRepository;
   }

   @Override
   public List<TopoDto> findAll() {
      List<TopoDto> topoDtoList = new ArrayList<>();
      TopoDto topoDto;
List<Topo> topos = topoRepository.findAll();

      for (Topo topo : topoRepository.findAll()) {
         topoDto = new TopoDto();

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

         if (topo.getManagerId() != null) {
            topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
         }
         if (topo.getClimberId() != null) {
            topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
         }

         topoDtoList.add(topoDto);
      }

      return topoDtoList;
   }

   @Override
   public List<TopoDto> findByManagerId(Integer id) {
      List<TopoDto> topoDtoList = new ArrayList<>();
      TopoDto topoDto;

      for (Topo topo : topoRepository.findByManagerId(id)) {
         topoDto = new TopoDto();

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

         if (topo.getManagerId() != null) {
            topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
         }
         if (topo.getClimberId() != null) {
            topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
         }

         topoDtoList.add(topoDto);
      }

      return topoDtoList;
   }

   @Override
   public TopoDto getOne(Integer id) {
      Topo topo = new Topo();
      topo = topoRepository.getOne(id);
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

      if (topo.getManagerId() != null) {
         topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
      }
      if (topo.getClimberId() != null) {
         topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
      }

      return topoDto;
   }

   @Override
   public Integer save(TopoDto topoDto) {
      Topo topo = new Topo();

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

      if (topoDto.getAliasManager() != null) {
         topo.setManagerId(userRepository.findByAlias(topoDto.getAliasManager()).getId());
      }
      if (topoDto.getAliasClimber() != null) {
         topo.setClimberId(userRepository.findByAlias(topoDto.getAliasClimber()).getId());
      }

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
      TopoDto topoDto;


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
               predicates.add(cb.equal(root.get("managerId"), userRepository.findByAlias(filter.getAliasManager()).getId()) );
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

            return cb.and(predicates.toArray(new Predicate[0]));
         }
      });

      for (Topo topo : topos) {
         topoDto = new TopoDto();

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
         log.info("\n\nINFO TOPO DATE :" + topoDto.getDate());
         topoDto.setDescription(topo.getDescription());
         topoDto.setTechnic(topo.getTechnic());
         topoDto.setStatus(topo.getStatus().toString());
         topoDto.setStatusAuto(topo.getStatusAuto());

         if (topo.getManagerId() != null) {
            topoDto.setAliasManager(userRepository.getOne(topo.getManagerId()).getAlias());
         }
         if (topo.getClimberId() != null) {
            topoDto.setAliasClimber(userRepository.getOne(topo.getClimberId()).getAlias());
         }

         topoDtos.add(topoDto);
      }

      return topoDtos;
   }


}
