import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Airport;
import org.junit.Assert;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
public class AirlinePersistenceTest extends DataPersistenceTest{

    //todo : start from the bottom ...

   /* @Inject
    EntityManager entityManager;
*/
    @Test
    public void canPersistAirline() throws Exception {

        Airline air = new Airline("AirFrance");
        entityManager().persist(air);
        assertNotNull(air.getId());

    }

    @Test(expected = PersistenceException.class)
    public void canNotPersistAirline() throws Exception {
        Airline air = new Airline();
        entityManager().persist(air);
        assertNull(air.getId());
    }

    @Test
    public void canRetrieveAirline() throws Exception {

        assertEquals("JetAir", entityManager().find(Airline.class, 1000).getName());
    }

}
