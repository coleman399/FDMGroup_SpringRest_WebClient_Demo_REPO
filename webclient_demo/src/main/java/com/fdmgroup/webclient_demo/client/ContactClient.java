package com.fdmgroup.webclient_demo.client;

import java.util.List;

import com.fdmgroup.webclient_demo.model.Contact;

public interface ContactClient {
    public List<Contact> retrieveContacts();

    public Contact retrieveContact(Long id);

    public Contact generateContact(Contact contact);

    public Contact amendContact(Long id, Contact contact);

    public void removeContact(Long id);
}
