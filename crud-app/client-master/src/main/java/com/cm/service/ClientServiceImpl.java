package com.cm.service;


import com.cm.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cm.entity.Client;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);

    static final int firstRecord = 0;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    AddressService addressService;

    @Override
    public Client getClientById(long clientId) {

        Optional<Client> oClient = clientRepository.findById(clientId);

        Client client = oClient.orElseThrow(() -> {
            return new RuntimeException(String.format("clinen id: %s was not found", clientId));
        });

        addressService.getById(client.getAddress().getId());
        client.setAddress(client.getAddress());

        return client;
    }

    @Override
    public Client getClientByName(String clientName) {

        List<Client> clients = clientRepository.findByName(clientName);

        if (clients.isEmpty()) {
            throw new RuntimeException(
                    String.format("clinen clientName: %s was not found", clientName));
        }

        Client client = clients.get(firstRecord);
        addressService.getById(client.getAddress().getId());
        client.setAddress(client.getAddress());

        return client;
    }

    @Override
    public Client updateClient(Client client) {

        addressService.update(client.getAddress());
        client = clientRepository.save(client);

        logger.debug("-------------updateClient-------------");
        logger.debug(clientRepository.findAll().toString());
        logger.debug("client: "+ client);
        logger.debug("clientId: "+ client.getId());
        logger.debug("--------------------------------");

        return client;
    }

    @Override
    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }
}