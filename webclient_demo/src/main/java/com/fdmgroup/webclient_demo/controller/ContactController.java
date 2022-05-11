package com.fdmgroup.webclient_demo.controller;

import java.util.List;

import com.fdmgroup.webclient_demo.model.Contact;
import com.fdmgroup.webclient_demo.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ContactController {
    
    private ContactService contactService;
    
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String goToIndex(Model model) {
        List<Contact> contacts = contactService.retrieveContacts();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/{id}")
    public String displayContact(@PathVariable("id") Long contactId, Model model) {
        Contact foundContact = contactService.retrieveContact(contactId);
        model.addAttribute("contact", foundContact);
        return "display-contact";
    }
}
