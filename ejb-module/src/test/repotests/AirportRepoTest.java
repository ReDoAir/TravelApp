import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Country;
import com.realdolmen.course.persistence.AirportRepo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 8/10/2015.
 */
public class AirportRepoTest extends DataPersistenceTest {

    private AirportRepo repo;
    private List<Airport> airports;
    private Airport airport;

    @Before
    public void init()throws Exception
    {
        repo = new AirportRepo();
        repo.em = entityManager();
        airports = new ArrayList<>();

    }


    @Test
    public void getAllAirportsTest() throws Exception
    {
        airports = repo.getAllAirports();
       resultListNotEmpty();

    }

    @Test
    public void getAllAirportsByCityTest() throws Exception
    {
        airports = repo.getAirportsByCity("London");
        resultListNotEmpty();
    }

    @Test
    public void getAllAirportsByCountryTest() throws Exception
    {
        //this is tested in CountryPersistenceTest and should give Denmark
        Country country = entityManager().find(Country.class,1);
        airports = repo.getAirportsByCountry(country);
        resultListNotEmpty();
    }

    @Test
    public void getAllAirportsByCode()throws Exception
    {
        //this test should actually give 1 back but a code is 3 letters, what if we give only 2 letters?? let's find out in next test
        airports = repo.getAirportsByCode("ABR");
        //first of all, list is not empty, but there is only 1 and it should be Aberdeen
        resultListNotEmpty();
        assertEquals(1,airports.size());
        assertEquals("Aberdeen", airports.get(0).getCity());

    }

    @Test
    public void getAllAirportsByCodeWithOnly2Chars() throws Exception
    {
        airports = repo.getAirportsByCode("AB");
        resultListNotEmpty();
        //like I Said in last test this should give more than 1 => I counted and it shoud be 14
        assertEquals(14,airports.size());

    }

    @Test
    public void getAirportByIdTest() throws Exception
    {
        Airport airport = repo.getAirportById(25);
        assertEquals("Ahmedabad",airport.getName());
    }

    private void resultListNotEmpty()
    {
        assertTrue(airports.size()>0);
    }
}
