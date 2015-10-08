import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Airport;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.persistence.FlightRepo;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDOAX36 on 8/10/2015.
 */
public class FlightRepoTest extends DataPersistenceTest {

    private FlightRepo repo;
    private List<Flight> flights;
    private DateFormat format;

    @Before
    public void init()
    {
        repo = new FlightRepo();
        repo.em = entityManager();

        flights = new ArrayList<>();
        format = new SimpleDateFormat("yyyy-MM-dd");
    }



    @Test
    public void getAllFlightsTest() throws Exception
    {
        flights = repo.getAllFlights();
        resultListNotEmpty();

    }

    @Test
    public void getAllFlightsByArrivalAirportTest() throws Exception
    {
        Airport a = entityManager().find(Airport.class,851);
        flights = repo.getAllFlightsByArrivalAirport(a);
        resultListNotEmpty();

    }
    @Test
    public void getAllFlightsByArrivalDate() throws Exception
    {
        Date date = format.parse("2015-10-10");
        System.out.println(date.toString());
        flights = repo.getAllFlightsByArrivalDate(date);
        resultListNotEmpty();
    }

    @Test
    public void getAllFlightByDepartureAirport() throws Exception
    {
        Airport a = entityManager().find(Airport.class, 521);
        flights = repo.getAllFlightsByDepartureAirport(a);
        resultListNotEmpty();

    }

    @Test
    public void getAllFlightsByDepartureDate() throws Exception
    {
        Date d = format.parse("2015-10-10");
        System.out.println(d.toString());
        flights = repo.getAllFlightsByDepartureDate(d);
        resultListNotEmpty();
    }

    @Test
    public void getAllFlightsByDepartureDateWithAvailableSeatsTest()throws Exception
    {
        Date d = format.parse("2015-10-10");
        System.out.println(d.toString());
        flights = repo.getAllFlightsByDepartureDateWithAvailableSeats(d);
        resultListNotEmpty();
        assertTrue(flights.size() < repo.getAllFlightsByDepartureDate(d).size());
    }
    @Test
    public void getAllFlightsByFlightCodePartTest()throws Exception
    {
        flights = repo.getAllFlightsByFlightCode("EDD");
        resultListNotEmpty();
        assertTrue(flights.size()>1);
    }
    @Test
    public void getAllFlightsByFlightCodeExactTest()throws Exception
    {
        flights = repo.getAllFlightsByFlightCode("EDD13");
        resultListNotEmpty();
        assertTrue(flights.size()==1);
    }


    private void resultListNotEmpty()
    {
        assertTrue(flights.size()>0);
    }
}
