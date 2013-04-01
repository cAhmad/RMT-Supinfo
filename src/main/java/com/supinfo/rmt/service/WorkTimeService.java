package com.supinfo.rmt.service;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.WorkTime;

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
public class WorkTimeService {

    @PersistenceContext
    private EntityManager em;

    public void addWorkTime(WorkTime workTime) {
        em.persist(workTime);
    }

    public WorkTime editWorkTime(WorkTime workTime) {
        return em.merge(workTime);
    }

    public void deleteWorkTime(WorkTime workTime) {
        em.remove(editWorkTime(workTime));
    }

    public WorkTime getById(Long id) {
        return em.find(WorkTime.class, id);
    }

    public List<WorkTime> getAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<WorkTime> query = criteriaBuilder.createQuery(WorkTime.class);
        query.from(WorkTime.class);
        return em.createQuery(query).getResultList();
    }

    public List<WorkTime> getByEmployee(Employee employee) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<WorkTime> query = criteriaBuilder.createQuery(WorkTime.class);
        Root<WorkTime> workTimeRoot = query.from(WorkTime.class);
        query.where(criteriaBuilder.equal(workTimeRoot.get("employee").as(Employee.class), employee));
        return em.createQuery(query).getResultList();
    }
}
