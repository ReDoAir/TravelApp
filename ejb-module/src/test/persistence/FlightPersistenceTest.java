import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.Plane;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;

public class FlightPersistenceTest extends DataPersistenceTest {

    private Flight flight;

    @Before
    public void init(){
        Instant instant = Instant.now();
        Plane plane = new Plane("DDDD", 200);
        Airline airline = new Airline("TestAir");
        Airport airport = new Airport("Depart", "DEP");
        Airport airport2 = new Airport("Arrival", "ARR");
        entityManager().persist(airline);
        entityManager().persist(plane);
        entityManager().persist(airport);
        entityManager().persist(airport2);
        flight = new Flight();
        flight.setFlightCode("ABC-123");
        flight.setPlane(plane);
        flight.setDepartureDate(Date.from(instant));
        flight.setArrivalDate(Date.from(instant));
        flight.setAirline(airline);
        flight.setDepartAirport(airport);
        flight.setArrivalAirport(airport2);
        flight.setAvailablePlaces(plane.getNumberOfSeats());
        flight.setPrice(123.123);
    }

    @Test
    public void flightCanBePersisted(){
        entityManager().persist(flight);
        assertEquals(flight.getFlightCode(), entityManager().find(flight.getClass(), flight.getId()).getFlightCode());
    }
}
