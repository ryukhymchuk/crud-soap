package crud.vaadin;

import apiws.ClientInfo;
import apiws.ClientRequest;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import org.vaadin.spring.events.EventBus;
import org.vaadin.viritin.fields.MTextField;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@UIScope
@SpringComponent
public class ClientForm extends AbstractForm<ClientInfo> {

    //private static final long serialVersionUID = 1L;

    EventBus.UIEventBus eventBus;

    TextField name = new MTextField("name");
    TextField surname = new MTextField("surname");
    TextField age = new MTextField("age");
    TextField country = new MTextField("country");
    TextField city = new MTextField("city");
    TextField street = new MTextField("street");
    TextField house = new MTextField("house");
    TextField apartment = new MTextField("apartment");

    ClientForm(ClientRequest clientRequest, EventBus.UIEventBus b) {
        super(ClientInfo.class);
        this.eventBus = b;

        // On save & cancel, publish events that other parts of the UI can listen
        setSavedHandler(clientInfo -> {
            // persist changes
            clientRequest.updateClient(clientInfo);
            eventBus.publish(this, new ClientModifiedEvent(clientInfo));
        });
        setResetHandler(p -> eventBus.publish(this, new ClientModifiedEvent(p)));

        setSizeUndefined();
    }

    @Override
    protected void bind() {
        getBinder().forMemberField(age)
                .withConverter(new StringToIntegerConverter("Is not a number"));

        getBinder().bind(country, "address.country");
        getBinder().bind(city, "address.city");
        getBinder().bind(street, "address.street");


        super.bind();
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new MFormLayout(
                        name,
                        surname,
                        age,
                        country,
                        city,
                        street
                ).withWidth(""),
                getToolbar()
        ).withWidth("");
    }

}
