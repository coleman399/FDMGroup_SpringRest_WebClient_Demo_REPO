package com.fdmgroup.webclient_demo.client;

import java.util.List;

import com.fdmgroup.exception.ContactNotFoundException;
import com.fdmgroup.webclient_demo.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Controller
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
                .onStatus(HttpStatus.NOT_FOUND::equals, ClientResponse::createException)
                .bodyToMono(Contact.class).block();
    }

    @Override
    public Contact generateContact(Contact contact) {
        return webClient.post().uri(builder -> builder.path("/api/v1/contacts").build()).bodyValue(contact).retrieve()
                .bodyToMono(Contact.class).block();
    }

    @Override
    public Contact amendContact(Long id, Contact contact) {
        return webClient.put().uri(builder -> builder.path("/api/v1/contacts/{id}").build(id)).bodyValue(contact)
                .retrieve().bodyToMono(Contact.class).block();
    }

    @Override
    public void removeContact(Long id) {
        webClient.delete().uri(builder -> builder.path("/api/v1/contacts/{id}").build(id)).retrieve().toBodilessEntity()
                .block();
    }

}
