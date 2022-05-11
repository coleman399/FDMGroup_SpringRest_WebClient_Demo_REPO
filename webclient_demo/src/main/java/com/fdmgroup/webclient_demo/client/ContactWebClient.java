package com.fdmgroup.webclient_demo.client;

import java.util.List;

import com.fdmgroup.exception.ContactNotFoundException;
import com.fdmgroup.webclient_demo.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ContactWebClient implements ContactClient {

    private WebClient webClient;

    @Autowired
    public ContactWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public List<Contact> retrieveContacts() {
        return webClient.get().uri(builder -> builder.path("/api/v1/contacts").build()).retrieve()
                .bodyToFlux(Contact.class).collectList().block();
    }

    @Override
    public Contact retrieveContact(Long id) {
        return webClient.get().uri(builder -> builder.path("/api/v1/contacts/{id}").build(id)).retrieve()
                .onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
                        response -> Mono.error(new ContactNotFoundException("No Contact Found With Id = " + id)))
                .bodyToMono(Contact.class).block();
    }

    @Override
    public Contact generateContact(Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Contact amendContact(Long id, Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeContact(Long id) {
        // TODO Auto-generated method stub

    }

}
