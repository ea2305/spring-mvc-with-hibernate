/**
 * Spring requirement - Client DAO in a Service Implemnetation - Basic CRUD Functions
 * @author Elihu A. Cruz
 * @version 1.0.1
 */

package com.client.demo.services;

import com.client.demo.models.Client;
import com.client.demo.persistence.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    // GenericDAO<Client,Long> clientDAO;
    ClientDAO clientDAO;

    @Override
    public List<Client> readAll() {
        return (List<Client>) this.clientDAO.readAll();
    }

    @Override
    public Client findOne(Long id) {
        return this.clientDAO.findOne( id );
    }

    @Override
    public Client create(Client client) {

        return this.clientDAO.create( client );
    }

    @Override
    public Client update(Client client) {
        return this.clientDAO.update( client );
    }

    @Override
    public boolean delete(Client client) {
        this.clientDAO.delete( client );
        return true;
    }
}
