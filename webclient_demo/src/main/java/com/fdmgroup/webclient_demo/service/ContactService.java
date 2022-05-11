package com.fdmgroup.webclient_demo.service;

import java.util.List;

import com.fdmgroup.webclient_demo.client.ContactClient;
import com.fdmgroup.webclient_demo.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    private ContactClient contactClient;

    @Autowired
    public ContactService(ContactClient contactClient) {
        this.contactClient = contactClient;
    }

    public List<Contact> retrieveContacts() {
        return contactClient.retrieveContacts();
    }

    public Contact retrieveContact(Long contactId) {
        return contactClient.retrieveContact(contactId);
    }
}
