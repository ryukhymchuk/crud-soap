package com.cm.service;


import com.cm.entity.Address;

public interface AddressService {

    void deleteAll();

    void update(Address address);

    Address getById(Long id);
}
