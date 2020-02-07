package com.dsf.escalade.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class VoieCompleteDto {
   private VoieDto voieDto;
   private List<LongueurCompleteDto> longueurCompleteDtos;
}
