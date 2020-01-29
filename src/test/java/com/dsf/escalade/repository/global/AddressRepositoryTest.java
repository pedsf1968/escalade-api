package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.business.Longueur;
import com.dsf.escalade.model.global.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void getOne() {
        Address address = new Address();

        address.setCity("Strasbourg");
        address.setCountry("FRANCE");
        address.setZipCode("68123");
        address.setStreet1("Résidence le Palmier");
        address.setStreet2("23, Rue de la République");

        address = addressRepository.save(address);
        Address addressGet = addressRepository.getOne(address.getId());
        assertEquals(addressGet.getCity(), address.getCity());
        assertEquals(addressGet.getCountry(),address.getCountry());
        assertEquals(addressGet.getZipCode(), address.getZipCode());
        assertEquals(addressGet.getStreet1(), address.getStreet1());
        assertEquals(addressGet.getStreet2(), address.getStreet2());
    }

    @Test
    void getId() {
        Address address = new Address();

        address.setCity("Strasbourg");
        address.setCountry("FRANCE");
        address.setZipCode("68123");
        address.setStreet1("Résidence le Palmier");
        address.setStreet2("23, Rue de la République");

        address = addressRepository.save(address);
        Integer addressId = addressRepository.getId(address.getStreet1(),address.getStreet2(), address.getCity(),address.getCountry(),address.getZipCode());
        assertEquals(addressId, address.getId());
    }
}