import com.realdolmen.course.domain.Airline;
import com.realdolmen.course.domain.Partner;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * Created by SDOAX36 on 6/10/2015.
 */
public class PartnerPersistenceTest extends DataPersistenceTest{


    private Partner p1;
    //we need him to fail!!!
    private Partner p2;
    private Airline airline;

    @Before
    public void init() throws Exception
    {
        p2 = new Partner();
        airline = new Airline("RyanAir");
        entityManager().persist(airline);
        p1 = new Partner("mldkfj","smldkfj",airline);

    }


    @Test
    public void canPersistPartner() throws Exception
    {

        entityManager().persist(p1);
        assertNotNull(p1.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void canNotPersistWithoutUsername()throws Exception
    {
        p2.setPassword("mlkdfj");
        p2.setAirline(airline);
        entityManager().persist(p2);
        assertNull(p2.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void canNotPersistWithoutAirline()throws Exception
    {
        p2.setPassword("mlkdfj");
        p2.setUsername("dmlkjf");
        entityManager().persist(p2);
        assertNull(p2.getId());
    }
    @Test(expected = PersistenceException.class)
    public void canNotPersistWhenUsernameExists()throws Exception
    {
        p2.setUsername("partner1");
        p2.setPassword("mlkdfj");
        p2.setAirline(airline);
        entityManager().persist(p2);
        assertNull(p2.getId());
    }

    @Test(expected = PersistenceException.class)
    public void canNotPersistWhenUsernameExistsInCustomerTable() throws Exception
    {
        p2.setUsername("customer1");
        p2.setPassword("pppposdqifds");
        p2.setAirline(airline);
        entityManager().persist(p2);
        assertNull(p2.getId());
    }

    @Test
    public void canRetrieveParnterById() throws Exception
    {
        assertEquals("partner1",entityManager().find(Partner.class,1200L).getUsername());
    }

}
