package com.dsf.escalade.service;

import com.dsf.escalade.web.dto.AddressDto;

public interface AddressService {
   AddressDto getOne(Integer id);
   Integer save(AddressDto addressDto);
}
