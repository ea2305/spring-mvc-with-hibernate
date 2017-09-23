package com.client.demo.controllers;

import com.client.demo.models.Client;
import com.client.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {

        // Spring JPA + Hibernate implementation
        List<Client> clients = this.clientService.readAll();

        ModelAndView modelAndView = new ModelAndView("/client/index");

        modelAndView.addObject("clients", clients);

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public  ModelAndView showCreateView() {
        ModelAndView modelAndView = new ModelAndView("/client/create");

        modelAndView.addObject( "client", new Client());

        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createEntity(@ModelAttribute(value = "client") @Valid Client client, @RequestParam("birthdate_update") String birthdate_update) {

        ModelAndView modelAndView = new ModelAndView( "/client/index");

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate new_birthdate = LocalDate.parse(birthdate_update, format);

        client.setFirst_name( client.getFirst_name().toUpperCase() );
        client.setLast_name( client.getLast_name().toUpperCase() );
        client.setBirthdate(new_birthdate);

        try {
            this.clientService.create( client );
            modelAndView.addObject("success","Client Created");
        }catch (Exception err){
            modelAndView.addObject("error","Error");
        }

        List<Client> clients = this.clientService.readAll();
        modelAndView.addObject("clients", clients);

        return modelAndView;
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateView(@PathVariable("id") Long id) {

        ModelAndView modelAndView = new ModelAndView("/client/update");

        Client client = this.clientService.findOne(id);

        modelAndView.addObject("client", client);

        return modelAndView;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateEntity(@ModelAttribute(value = "client") @Valid Client client, @RequestParam("birthdate_update") String birthdate_update, BindingResult result) {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate new_birthdate = LocalDate.parse(birthdate_update, format);

        client.setBirthdate(new_birthdate);

        System.out.println(" DATA -> " + client.toString() + " - " + birthdate_update);

        this.clientService.update(client);

        return "redirect:/client";

    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView showDeleteView(@PathVariable("id") Long id) {

        ModelAndView modelAndView = new ModelAndView("/client/delete");

        try {
            Client client = this.clientService.findOne(id);
            modelAndView.addObject("id", client.getId() );
            modelAndView.addObject("name", client.getFirst_name() );

        } catch ( Exception err ) {
            modelAndView.addObject("errors", "Error !!" );
        }

        return modelAndView;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deleteEntity( @RequestParam("id") Long id, @RequestParam("name") String name ) {

        ModelAndView modelAndView = new ModelAndView("/client/index");

        Client client = this.clientService.findOne(id);

        String name_string = client.getFirst_name();

        if (! Objects.equals(name, name_string)){

            modelAndView.addObject("errors","The name does not match");

        } else {

            try{
                this.clientService.delete( client );
                modelAndView.addObject("success","Success");
            } catch (Exception e) {
                modelAndView.addObject("errors","The name does not match");
            }

        }

        List<Client> clients = this.clientService.readAll();
        modelAndView.addObject("clients", clients);

        return modelAndView;

    }

}
