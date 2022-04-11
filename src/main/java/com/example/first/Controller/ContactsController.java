package com.example.first.Controller;

import com.example.first.Models.ContactsDb;
import com.example.first.repos.ContactsDbRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactsController {

    @Autowired
    private ContactsDbRepo contactsDbRepo;

    @GetMapping("/MyContacts")
    public String contactsMain(Model model) {
        Iterable<ContactsDb> contactsDbs = contactsDbRepo.findAll();
        model.addAttribute("ContactsDbs", contactsDbs);

        return "contacts-main";
    }

    @GetMapping("/MyContacts/Add")
    public String addcontact(Model model) {
        return "contact";
    }

    @PostMapping("/MyContacts/Add")
    public String postaddcontact(@RequestParam String name, @RequestParam String last_name,@RequestParam String email, @RequestParam Integer number, Model model) {
        ContactsDb contactsDb = new ContactsDb(name, last_name, email, number);
        contactsDbRepo.save(contactsDb);
        return "redirect:/MyContacts";
    }
}
