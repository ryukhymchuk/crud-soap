package com.cm.service.service.repository;


import com.cm.SimpleSoapApplication;
import com.cm.fixture.MockDataSupplier;
import com.cm.entity.Address;
import com.cm.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleSoapApplication.class)
public class AddressRepositoryTest {


    @Autowired
    AddressRepository addressRepository;

    @Test
    public void ifAddressIsGiven_saveAddressInDbAndReturnFromDb() {


        Address expectedAddress = MockDataSupplier.getAddress();

        addressRepository.save(expectedAddress);


        Address actualAddress = addressRepository.getAddressByCity(expectedAddress.getCity());

        assertThat(expectedAddress.getCity(), is(equalTo(actualAddress.getCity())));

    }

}