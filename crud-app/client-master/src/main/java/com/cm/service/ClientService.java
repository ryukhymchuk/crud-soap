package com.cm.service;


import com.cm.entity.Client;

public interface ClientService {

    Client getClientById(long clientId);

    Client getClientByName(String clientName);

    Client updateClient(Client article);

    void deleteClientById(Long id);

    void deleteAll();
}