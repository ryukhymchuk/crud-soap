package com.cm.service.service.service;

import com.cm.fixture.MockDataSupplier;
import com.cm.service.AddressService;
import com.cm.SimpleSoapApplication;
import com.cm.entity.Client;
import com.cm.service.ClientService;
import org.junit.After;
import org.junit.Before;
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
public class ClientServiceIntegrationTest {


    @Autowired
    ClientService clientService;

    @Autowired
    AddressService addressService;

    Client expectedClient = MockDataSupplier.getClient();


    @Before
    public void testUp() {
        addressService.update(expectedClient.getAddress());
        Client c = clientService.updateClient(expectedClient);
    }

    @After
    public void tearDown() {
        clientService.deleteAll();
    }

    @Test
    public void getClientById() throws Exception {


        Client actualClient = clientService.getClientByName(expectedClient.getName());

        assertThat(actualClient.getSurname(), is(equalTo(actualClient.getSurname())));
    }

    @Test
    public void getClientByName() throws Exception {

        Client actualClient = clientService.getClientByName(expectedClient.getName());

        assertThat(actualClient.getSurname(), is(equalTo(expectedClient.getSurname())));

    }

}