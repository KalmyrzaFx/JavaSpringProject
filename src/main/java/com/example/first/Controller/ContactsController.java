package com.example.first.Controller;

import com.example.first.Models.ContactsDb;
import com.example.first.repos.ContactsDbRepo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

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

    @GetMapping("/MyContacts/{id}/edit")
    public String contactEdit(@PathVariable(value = "id") long id, Model model) {
        if(!contactsDbRepo.existsById(id)) {
            return "redirect:/MyContacts";
        }

        Optional<ContactsDb> contactsDb = contactsDbRepo.findById(id);
        ArrayList<ContactsDb> result = new ArrayList<>();
        contactsDb.ifPresent(result::add);
        model.addAttribute("ContactDb", result);
        return "contact-edit";
    }

    @PostMapping("/MyContacts/{id}/edit")
    public String postupdatecontact(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String last_name,@RequestParam String email, @RequestParam Integer number, Model model) {

        ContactsDb contactsDb = contactsDbRepo.findById(id).orElseThrow(IllegalStateException::new);
        contactsDb.setName(name);
        contactsDb.setLast_name(last_name);
        contactsDb.setEmail(email);
        contactsDb.setNumber(number);
        contactsDbRepo.save(contactsDb);
        return "redirect:/MyContacts";
    }

    @PostMapping("/MyContacts/{id}/delete")
    public String postdeletecontact(@PathVariable(value = "id") long id, Model model) {

        ContactsDb contactsDb = contactsDbRepo.findById(id).orElseThrow(IllegalStateException::new);
        contactsDbRepo.delete(contactsDb);
        return "redirect:/MyContacts";
    }
}
