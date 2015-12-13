package us.juggl.twentyfifteen.december.jpa.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import us.juggl.twentyfifteen.december.jpa.entities.Person;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dphillips on 12/12/15.
 */
@AllArgsConstructor
@Slf4j
public class PersonDAO {

    private final EntityManagerFactory emf;

    public List<Person> getAllPeople() {
        return emf.createEntityManager().createQuery("from Person").getResultList();
    }

    public Person getPerson(Long id) {
        return emf.createEntityManager().find(Person.class, id);
    }

    public Person updatePerson(Person person) {
        return emf.createEntityManager().merge(person);
    }

    public Person addPerson(Person person) throws PersistenceException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(person);
            em.flush();
            t.commit();
        } catch (PersistenceException pe) {
            LOG.error(pe.getLocalizedMessage(), pe);
            t.rollback();
            throw pe;
        }
        return person;
    }
}
