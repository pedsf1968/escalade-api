package com.dsf.escalade.service.global;

import com.dsf.escalade.model.global.Address;
import com.dsf.escalade.repository.global.AddressRepository;
import com.dsf.escalade.web.dto.AddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {
   private final AddressRepository addressRepository;

   @Autowired
   public AddressServiceImpl(AddressRepository addressRepository) {
      this.addressRepository = addressRepository;
   }

   @Override
   public AddressDto getOne(Integer id) {
      if(id == null){
         return null;
      }

      Address address = addressRepository.getOne(id);
      AddressDto addressDto = new AddressDto();

      addressDto.setId(address.getId());
      addressDto.setStreet1(address.getStreet1());
      addressDto.setStreet2(address.getStreet2());
      addressDto.setZipCode(address.getZipCode());
      addressDto.setCity(address.getCity());
      addressDto.setCountry(address.getCountry());

      return addressDto;
   }

   @Override
   public Integer save(AddressDto addressDto) {

      // Does the address exist?
      Integer addressId = addressRepository.getId(
            addressDto.getStreet1(),
            addressDto.getStreet2(),
            addressDto.getCity(),
            addressDto.getCountry(),
            addressDto.getZipCode());

      if( addressId == null) {
         // address doesn't exist create a new one
         Address address = new Address();

         address.setId(addressDto.getId());
         address.setStreet1(addressDto.getStreet1());
         address.setStreet2(addressDto.getStreet2());
         address.setZipCode(addressDto.getZipCode());
         address.setCity(addressDto.getCity());
         address.setCountry(addressDto.getCountry());

         addressId = addressRepository.save(address).getId();
      }

      return addressId;
   }
}
