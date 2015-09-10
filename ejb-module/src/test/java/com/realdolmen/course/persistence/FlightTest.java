package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.DomesticFlight;
import com.realdolmen.course.domain.Flight;
import com.realdolmen.course.domain.InternationalFlight;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightTest extends DataSetPersistenceTest {

    @Test
    public void domesticFlightCanBePersisted(){
        Flight domFlight = new DomesticFlight("5555",new Date(),new Date(),"Brussels Airlines",new ArrayList<>());
        entityManager().persist(domFlight);
        assertNotNull(domFlight.getId());
    }

    @Test
    public void internationalFlightCanBePersisted(){
        Flight internationalFlight = new InternationalFlight("6666",new Date(),new Date(), false, "testString");
        entityManager().persist(internationalFlight);
        assertNotNull(internationalFlight.getId());
    }
}
