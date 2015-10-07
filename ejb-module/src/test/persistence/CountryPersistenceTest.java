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
        assertEquals("Belgium", entityManager().find(Country.class, 1552).getName());
    }

    @Test
    public void canRetrieveCountryAirportList()throws Exception
    {
        assertEquals(2,entityManager().find(Country.class,1552).getAirports().size());
    }
}
