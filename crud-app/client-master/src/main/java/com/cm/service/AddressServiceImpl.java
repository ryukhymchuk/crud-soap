package com.cm.service;

import com.cm.entity.Address;
import com.cm.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void deleteAll() {
        addressRepository.deleteAll();
    }

    @Override
    public void update(Address address) {
        addressRepository.save(address);
    }

    @Override
    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException(String.format("address id% has not been found"));
        });
    }
}
