package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
   Address getOne(Integer addressId);

   @Query("SELECT a.id FROM Address a WHERE a.street1 = ?1 AND a.street2 = ?2 AND a.city = ?3 AND a.country = ?4 AND a.zipCode = ?5" )
   Integer getId(String street1, String street2, String city, String country, String zipCode);
}
