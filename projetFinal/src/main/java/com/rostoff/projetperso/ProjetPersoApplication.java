package com.rostoff.projetperso;

import com.rostoff.projetperso.dao.ContactRepository;
import com.rostoff.projetperso.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class ProjetPersoApplication implements CommandLineRunner {
    @Autowired
    private ContactRepository contactRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetPersoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        contactRepository.save(new Contact("COUCHY", "Christophe", df.parse("23/06/1981"), "kitsuninja@hotmail.com", "0674944590", "chris.jpg"));
        contactRepository.save(new Contact("ICHIGO", "Kurosaki", df.parse("20/09/1991"), "zanpakuto@hotmail.com", "0674944590", "kurosaki.jpg"));
        contactRepository.save(new Contact("YAEGER", "Eren", df.parse("13/06/1999"), "titan@hotmail.com", "0674944590", "eren.jpg"));
        contactRepository.findAll().forEach(c ->{
            System.out.println(c.getNom());
        });
    }
}
