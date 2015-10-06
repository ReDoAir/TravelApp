import com.realdolmen.course.domain.Airport;
import org.junit.Test;

/**
 * Created by SDOAX36 on 5/10/2015.
 */
public class AirportPersistenceTest extends DataPersistenceTest{

    @Test
    public void canAddAirportTest()throws Exception{
        Airport airport = new Airport("Charles De Gaulle","FRCDG123",12.322);
        entityManager().persist(airport);
        assertNotNull(airport.getId());

    }
}
