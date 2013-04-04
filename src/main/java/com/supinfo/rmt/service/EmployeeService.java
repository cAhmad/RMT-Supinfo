package com.supinfo.rmt.service;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;

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
public class EmployeeService {

    @PersistenceContext(unitName = "RMT-PU")
    private EntityManager em;

    public void addEmployee(Employee employee) {
        em.persist(employee);
    }

    public Employee editEmployee(Employee employee) {
        return em.merge(employee);
    }

    public Employee getById(final long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> getAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        query.from(Employee.class);
        return em.createQuery(query).getResultList();
    }

    public List<Employee> getByManager(Manager manager) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> query = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = query.from(Employee.class);
        query.where(criteriaBuilder.equal(employeeRoot.get("manager").as(Manager.class), manager));
        return em.createQuery(query).getResultList();
    }

}
