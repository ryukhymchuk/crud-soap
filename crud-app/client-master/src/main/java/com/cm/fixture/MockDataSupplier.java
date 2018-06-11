package com.cm.fixture;


import com.cm.entity.Address;
import com.cm.entity.Client;

public final class MockDataSupplier {

    private MockDataSupplier() {
    }


    public static Address getAddress() {

        Address address = new Address();

        address.setCity("krakow");
        address.setCountry("poland");
        address.setApartment(10);
        address.setHouse(1);
        address.setStreet("ul test");

        return address;
    }

    public static Client getClient() {

        Client client = new Client();

        client.setAge(10);
        client.setName("test_clinet");
        client.setSurname("test_surname");
        client.setAddress(MockDataSupplier.getAddress());
        return client;
    }

}
