import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.persistence.AirlineRepository;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static com.realdolmen.course.domain.Airline.FIND_AIRLINE_BY_NAME_Q;
import static com.realdolmen.course.domain.Airline.FIND_ALL_AIRLINES_Q;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
public class AirlineRepositoryTest extends DataPersistenceTest {

    //todo : implement the shit

    private AirlineRepository repo;

    @Before
    public void init(){
        repo = new AirlineRepository();
        repo.em = entityManager();
    }

    @Test
    public void findAllAirlinesTest() throws Exception
    {
        List<Airline>airlines = repo.findAllAirlines();
        assertTrue(airlines.size() > 0);
    }

    @Test
    public void findAllAirlinesWithNameTest()throws Exception
    {
        //this case should give only 1 back, so let's also check it => but in real life cases this will fail because it is possible to have a similar name or start of a name, see next test
        List<Airline> airlines = repo.findAirlinesByName("JetAir");
        assertTrue(airlines.size() > 0);
        assertEquals(1, airlines.size());
    }

    @Test
    public void findAllAirlinesWithPartOfNameTest()throws Exception
    {
        List<Airline> airlines = repo.findAirlinesByName("Thomas");
        assertTrue(airlines.size() > 0);
        assertEquals(2,airlines.size());

    }

    @Test
    public void findAirlineByIdTest()throws Exception
    {
        Airline airline = repo.findAirlineById(1000);
        assertEquals("JetAir",airline.getName());
    }

    @Test
    public void addAirlineTest() throws Exception
    {
        Airline airline = new Airline("YoMamaTest");
        repo.addAirline(airline);
        assertNotNull(airline.getId());
    }

    @Test
    public void removeAirlineTest() throws Exception
    {
        Airline airline = repo.findAirlineById(1000);
        repo.removeAirline(airline);
        assertNull(repo.findAirlineById(1000));
    }

}
