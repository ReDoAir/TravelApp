import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Country;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 6/10/2015.
 */
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
        assertEquals(7,entityManager().find(Country.class,1).getAirports().size());
    }
}
