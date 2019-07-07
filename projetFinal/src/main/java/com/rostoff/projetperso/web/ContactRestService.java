package com.rostoff.projetperso.web;

import com.rostoff.projetperso.dao.ContactRepository;
import com.rostoff.projetperso.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class ContactRestService {
    @Autowired
    private ContactRepository contactRepository;

    //Trouver toute la liste
    @RequestMapping(value="/contacts", method = RequestMethod.GET)
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    //Trouver un élément à la fois par ID
    @RequestMapping(value="/contacts/{id}", method = RequestMethod.GET)
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    //Ajouter un élément
    @RequestMapping(value="/contacts", method = RequestMethod.POST)
    public Contact addContact(@RequestBody Contact c){
        return contactRepository.save(c);
    }

    //Supprimer un élément à la fois par ID
    @RequestMapping(value="/contacts/{id}", method = RequestMethod.DELETE)
    public boolean deleteContactById(@PathVariable Long id){
        contactRepository.deleteById(id);
        return true;
    }

    //Modifier un élément
    @RequestMapping(value="/contacts/{id}", method = RequestMethod.PUT)
    public Contact modifyContact(@PathVariable Long id, @RequestBody Contact c){
        c.setId(id);
        return contactRepository.save(c);
    }

    //Chercher un élément par ID
    @RequestMapping(value="/chercher", method = RequestMethod.GET)
    public Page<Contact> chercher(
            @RequestParam(name="mc", defaultValue = "") String mc,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size) {
        return contactRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
    }


}
