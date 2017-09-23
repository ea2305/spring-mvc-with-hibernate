/**
 * Client DAO implementation - Basic CRUD Functions
 * @author Elihu A. Cruz
 * @version 1.0.1
 */

package com.client.demo.persistence;

import com.client.demo.models.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ClientDAOImpl implements ClientDAO{


    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }


    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all elements
     * @return
     */
    @Override
    public List<Client> readAll() {

        // Get all data from data base
        List<Client> clients = (List<Client>) entityManager.createQuery("FROM Client").getResultList();

        return clients;
    }

    @Override
    public Client findOne(Long id) {
        // Find and return data from data base
        return this.entityManager.find(Client.class, id);
    }

    /**
     * Persist object in database
     * @param t : Client Object with data to insert
     * @return
     */
    @Override
    public Client create(Client t) {

        // Realizamos la inserción
        this.entityManager.persist(t);

        return t;
    }

    /**
     * Update data
     * @param t : Update data of client
     * @return
     */
    @Override
    public Client update(Client t) {

        // Realizamos la inserción
        Client update = this.entityManager.merge(t);

        return update;

    }

    /**
     * Delete element by Object
     * @param t : Id to find client in database
     * @return
     */
    @Override
    public boolean delete(Client t) {

        t = this.entityManager.merge(t);
        this.entityManager.remove(t);

        return false;
    }

}
