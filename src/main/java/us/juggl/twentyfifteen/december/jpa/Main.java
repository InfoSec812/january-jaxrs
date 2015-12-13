package us.juggl.twentyfifteen.december.jpa;

import lombok.extern.slf4j.Slf4j;
import us.juggl.twentyfifteen.december.jpa.dao.PersonDAO;
import us.juggl.twentyfifteen.december.jpa.entities.Person;

import javax.persistence.*;

/**
 * Created by dphillips on 12/13/15.
 */
@Slf4j
public class Main {

    public static void main(String... args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("december_jpa");
        PersonDAO dao = new PersonDAO(emf);

        Person deven = new Person(null, "Deven", "Phillips", 41);

        LOG.debug("Persisting: "+deven.toString());
        Person persistedDeven = dao.addPerson(deven);
        LOG.debug("Persisted: "+persistedDeven.toString());

        Person deven2 = new Person(null, "Deven", "Phillips", 41);

        LOG.debug("Persisting 2nd copy of: "+deven2.toString());
        try {
            dao.addPerson(deven2);
        } catch (PersistenceException e) {
            LOG.error("Failed to persist deven2 because of constraints.", e);
        }

        emf.close();
        return;
    }
}
