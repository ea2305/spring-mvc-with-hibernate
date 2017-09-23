/**
 * Client DAO Interface - Basic CRUD Functions
 * @author Elihu A. Cruz
 * @version 1.0.1
 */

package com.client.demo.persistence;

import com.client.demo.models.Client;
import java.util.List;

public interface ClientDAO {

    /**
     * Get all values inside of database
     * @return List of Clients
     */
    List<Client> readAll();

    /**
     * Get an especific element by id
     * @param id : Client Id
     * @return {Client} Object
     */
    Client findOne(Long id );

    /**
     * Percistence operation. "Insert to database"
     * @param client : Client Object with data to insert
     * @return Boolean
     */
    Client create(Client client);

    /**
     * Update information in database
     * @param client : Update data of client
     * @return
     */
    Client update(Client client);

    /**
     * Delete client
     * @param client : Id to find client in database
     */
    boolean delete(Client client);

}
