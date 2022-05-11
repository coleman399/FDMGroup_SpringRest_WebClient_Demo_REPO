package com.fdmgroup.webclient_demo.controller;

import java.util.List;

import com.fdmgroup.webclient_demo.model.Contact;
import com.fdmgroup.webclient_demo.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String displayContact(@PathVariable("id") Long id, Model model) {
        Contact foundContact = contactService.retrieveContact(id);
        model.addAttribute("contact", foundContact);
        return "display-contact";
    }

    @GetMapping("/createContact")
    public String goToCreateContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "create-contact";
    }

    @PostMapping("/createContact")
    public String createContact(Model model, Contact contact) {
        contactService.generateContact(contact);
        return "redirect:/";
    }

    @GetMapping("/updateContact/{id}")
    public String updateContact(@PathVariable("id") Long id, Model model) {
        Contact foundContact = contactService.retrieveContact(id);
        model.addAttribute("contact", foundContact);
        return "update-contact";
    }

    @PostMapping("/updateContact/{id}")
    public String updateContact(@PathVariable("id") Long id, Contact contact) {
        contactService.amendContact(id, contact);
        return "redirect:/";
    }

    @GetMapping("/deleteContact/{id}")
    public String deleteContact(@PathVariable("id") Long id) {
        contactService.removeContact(id);
        return "redirect:/";
    }
}
