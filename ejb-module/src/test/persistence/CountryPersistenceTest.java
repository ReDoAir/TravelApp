import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Country;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CountryPersistenceTest extends DataPersistenceTest {

    @Test
    public void canPersistCountry() throws Exception{
        Country country = new Country();
        country.setName("Belgie");
        List<Airport>airports = new ArrayList<>();
        airports.add(new Airport("Brussels central","BE1111"));
        airports.add(new Airport("Brussels south","BE23113"));
        entityManager().persist(country);

        assertNotNull(country.getId());
    }

    @Test
    public void canRetreveCountry()throws Exception
    {
        assertEquals("Denmark", entityManager().find(Country.class, 1).getName());
    }

    @Test
    public void canRetrieveCountryAirportList()throws Exception
    {
        //todo : fix test
        assertEquals(7,entityManager().find(Country.class, 1).getAirports().size());
    }

    @Test
    public void canRetrieveAirportsByCountry(){
        List<String> list = entityManager ().createQuery("SELECT a.city FROM Country c JOIN c.airports a WHERE c.name = :name", String.class).setParameter("name", "Belgium").getResultList();
        assertTrue(3 == list.size());
    }
}
