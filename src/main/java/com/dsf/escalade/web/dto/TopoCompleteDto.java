package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopoCompleteDto {
   private TopoDto topoDto;
   private List<SectorDto> sectorDtos;
   private List<VoieDto> voieDtos;
}
