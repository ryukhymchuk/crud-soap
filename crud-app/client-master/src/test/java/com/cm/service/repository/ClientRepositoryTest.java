package com.cm.service.repository;


import com.cm.SimpleSoapApplication;
import com.cm.fixture.MockDataSupplier;
import com.cm.repository.ClientRepository;
import com.cm.entity.Client;
import com.cm.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SimpleSoapApplication.class)
public class ClientRepositoryTest {


    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AddressRepository addressRepository;

    @Test
    public void ifAddressIsGiven_saveAddressInDbAndReturnFromDb() {

        Client expectedClient = MockDataSupplier.getClient();

        addressRepository.save(expectedClient.getAddress());
        clientRepository.save(expectedClient);

        List<Client> actualClientList =  clientRepository.findByName(expectedClient.getName());

        Client actualClient  = actualClientList.get(0);

        assertThat(expectedClient.getName(), is(equalTo(actualClient.getName())));
        assertThat(expectedClient.getAddress().getApartment(), is(equalTo(actualClient.getAddress().getApartment())));

    }


}