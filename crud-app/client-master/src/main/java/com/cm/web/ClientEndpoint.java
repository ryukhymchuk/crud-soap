package com.cm.web;



import apiws.*;
import com.cm.entity.Address;
import com.cm.entity.Client;
import com.cm.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class ClientEndpoint {

    private static final Logger logger = LogManager.getLogger(ClientEndpoint.class);

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    @Autowired
    private ClientService clientService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getClientByIdRequest")
    @ResponsePayload
    public GetClientResponse getClientById(@RequestPayload GetClientByIdRequest request) {

        GetClientResponse response = mapClientToClientResponse(clientService.getClientById(request.getId()));

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchClientByNameRequest")
    @ResponsePayload
    public GetClientResponse searchClientByName(@RequestPayload SearchClientByNameRequest request) {

        Client client = clientService.getClientByName(request.getName());
        GetClientResponse response = mapClientToClientResponse(client);

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateClientRequest")
    @ResponsePayload
    public GetClientResponse updateClient(@RequestPayload UpdateClientRequest request) {

        Client client = mapRequestClientToClient(request);

        client = clientService.updateClient(client);

        GetClientResponse response = mapClientToClientResponse(client);

        return response;
    }

    private Client mapRequestClientToClient(@RequestPayload UpdateClientRequest request) {
        Client client = new Client();
        client.setAddress(new Address());
        BeanUtils.copyProperties(request.getClient(), client);
        BeanUtils.copyProperties(request.getClient().getAddress(), client.getAddress());
        client.setId(request.getClient().getId() == 0 ? null : client.getId());
        return client;
    }

    private GetClientResponse mapClientToClientResponse(Client client) {

        ClientInfo clientInfo = new ClientInfo();
        GetClientResponse response = new GetClientResponse();
        BeanUtils.copyProperties(client, clientInfo);
        clientInfo.setAddress(new AddressInfo());
        BeanUtils.copyProperties(client.getAddress(), clientInfo.getAddress());
        response.setClient(clientInfo);
        return response;
    }

}