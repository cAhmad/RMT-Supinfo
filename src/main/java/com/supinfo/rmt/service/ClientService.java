package com.supinfo.rmt.service;

import com.supinfo.rmt.entity.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Ahmad
 * @version $Id$
 */
@Stateless
public class ClientService {

    @PersistenceContext(unitName = "RMT-PU")
    private EntityManager em;

    public void addClient(Client client) {
        em.persist(client);
    }

    public Client editClient(Client client) {
        return em.merge(client);
    }

    public void deleteClient(Client client) {
        em.remove(client);
    }

    public Client getById(final long id) {
        return em.find(Client.class, id);
    }

    public List<Client> getAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        query.from(Client.class);
        return em.createQuery(query).getResultList();
    }

    public Client getByName(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = query.from(Client.class);
        query.where(criteriaBuilder.equal(clientRoot.get("name").as(String.class), name));
        query.distinct(true);
        return em.createQuery(query).getSingleResult();
    }
}
