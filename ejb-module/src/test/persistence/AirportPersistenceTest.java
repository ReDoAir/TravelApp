import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Country;
import org.junit.Before;
import org.junit.Test;

public class AirportPersistenceTest extends DataPersistenceTest{

    private Country country;

    @Before
    public void init(){
        country = new Country("Belgium");
        entityManager().persist(country);
    }

    @Test
    public void canAddAirportTest()throws Exception{
        Airport airport = new Airport("Charles De Gaulle","FRCDG123");
        airport.setCountry(country);
        airport.setCity("Brussels");
        entityManager().persist(airport);
        assertNotNull(airport.getId());

    }
}
