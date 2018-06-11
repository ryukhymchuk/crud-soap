package com.cm.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.cm.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

    Address getAddressByCity(String city);
}
