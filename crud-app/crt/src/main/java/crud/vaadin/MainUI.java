package crud.vaadin;


import apiws.ClientInfo;
import apiws.ClientRequest;
import apiws.GetClientResponse;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.UI;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.vaadin.spring.events.EventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@Title("Client Review Tool")
@Theme("valo")
@SpringUI
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ClientRequest.class);

    ClientForm personForm;
    EventBus.UIEventBus eventBus;
    ClientRequest clientRequest;

    private MGrid<ClientInfo> list = new MGrid<>(ClientInfo.class)
            .withProperties("id","name","surname","age")
            .withColumnHeaders("id", "Name", "Surname", "Age")
            .withFullWidth();

    private MTextField filterByName = new MTextField()
            .withPlaceholder("Filter by name");

    private MTextField filterById = new MTextField()
            .withPlaceholder("Search by id");
    private Button edit = new MButton(VaadinIcons.PENCIL, this::edit);
    private Button search = new MButton(VaadinIcons.SEARCH, this::search);

    public MainUI(ClientForm f, EventBus.UIEventBus b, ClientRequest clientRequest) {
        this.personForm = f;
        this.eventBus = b;
        this.clientRequest = clientRequest;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(
                new MVerticalLayout(
                        new MHorizontalLayout(filterById, filterByName, search, edit),
                        list
                ).expand(list)
        );

        list.addColumn(clientInfo -> clientInfo.getAddress().getCountry()).setCaption("Country").setId("country");
        list.addColumn(clientInfo -> clientInfo.getAddress().getCity()).setCaption("City").setId("city");
        list.addColumn(clientInfo -> clientInfo.getAddress().getStreet()).setCaption("Street").setId("street");
        list.addColumn(clientInfo -> clientInfo.getAddress().getHouse()).setCaption("House").setId("house");
        list.addColumn(clientInfo -> clientInfo.getAddress().getApartment()).setCaption("Apartment").setId("apartment");

        list.asSingleSelect().addValueChangeListener(e -> {
            adjustActionButtonState();
        });

        // Listen to change events emitted by ClientForm see onEvent method
        eventBus.subscribe(this);
    }

    protected void adjustActionButtonState() {
        boolean hasSelection = !list.getSelectedItems().isEmpty();
        edit.setEnabled(hasSelection);
    }


    private void listEntities(GetClientResponse response) {

        List<ClientInfo> clients = new ArrayList<>();
        clients.add(response.getClient());
        list.setItems(clients);

        adjustActionButtonState();
    }


    public void edit(ClickEvent e) {
        edit(list.asSingleSelect().getValue());
    }


    protected void edit(final ClientInfo client) {
        personForm.setEntity(list.asSingleSelect().getValue());
        personForm.openInModalPopup();
    }

    protected void search(ClickEvent e){

        GetClientResponse response;

        try {
            if (!filterById.getValue().equals("")) {
                response = clientRequest.getClientById(Integer.parseInt(filterById.getValue()));
            } else {
                response = clientRequest.searchClientByName(filterByName.getValue());
            }
            listEntities(response);
        } catch (Exception ex){
            log.info("Request has been failed ...");
        }

    }


    @EventBusListenerMethod(scope = EventScope.UI)
    public void onPersonModified(ClientModifiedEvent event) {
        //listEntities();
        personForm.closePopup();
    }

}
