package crud.vaadin;


import apiws.ClientInfo;

import java.io.Serializable;

public class ClientModifiedEvent implements Serializable {

    private final ClientInfo client;

    public ClientModifiedEvent(ClientInfo p) {
        this.client = p;
    }

    
}
