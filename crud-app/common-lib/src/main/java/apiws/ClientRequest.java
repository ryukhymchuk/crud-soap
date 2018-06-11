package apiws;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class ClientRequest extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(ClientRequest.class);

    public GetClientResponse getQuote(int id) {

        GetClientByIdRequest request = new GetClientByIdRequest();
        request.setId(id);
        log.info("Requesting client by id" + id);

        GetClientResponse response = (GetClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8989/ws",
                        request,
                        new SoapActionCallback("http://www.webserviceX.NET/GetQuote"));

        return response;
    }



    public GetClientResponse getClientById(int id) {
            log.info("Requesting client by id");

        GetClientByIdRequest request = new GetClientByIdRequest();
        request.setId(id);

            GetClientResponse response = (GetClientResponse) getWebServiceTemplate()
                    .marshalSendAndReceive("http://localhost:8989/ws",
                            request,
                            new SoapActionCallback("http://www.webserviceX.NET/getClientById"));

            return response;
    }


    public GetClientResponse searchClientByName(String name) {
           log.info("Requesting client by name");

        SearchClientByNameRequest request = new SearchClientByNameRequest();
        request.setName(name);

        GetClientResponse response = (GetClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8989/ws",
                        request,
                        new SoapActionCallback("http://www.webserviceX.NET/searchClientByName"));

        return response;
    }


    public GetClientResponse updateClient(ClientInfo clientInfo) {
        log.info("Requesting client by id");

        UpdateClientRequest request = new UpdateClientRequest();
        request.setClient(clientInfo);

        GetClientResponse response = (GetClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8989/ws",
                        request,
                        new SoapActionCallback("http://www.webserviceX.NET/updateClient"));

        return response;
    }
}