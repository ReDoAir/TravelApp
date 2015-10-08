import com.realdolmen.course.domain.Booking;
import com.realdolmen.course.domain.auth.Customer;
import com.realdolmen.course.persistence.BookingRepo;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDOAX36 on 8/10/2015.
 */
public class BookingRepoTest extends DataPersistenceTest {

    private BookingRepo repo;
    private List<Booking> bookings;

    @Before
    public void init()
    {
        repo = new BookingRepo();
        repo.em =entityManager();
        bookings = new ArrayList<>();
    }

    @Test
    public void getAllBookingsTest() throws Exception
    {

        bookings = repo.findAllBookings();
        resultListNotEmpty();
    }

    @Test
    public void getAllBookingsByCustomer() throws Exception
    {
        //already tested and succeed in CustomerPersistenceTest
        Customer c = entityManager().find(Customer.class,1300);
        bookings = repo.findAllBookingsByCustomer(c);
        resultListNotEmpty();
    }

    @Test
    public void getAllBookingsByBookingDate()throws Exception
    {
        String dateString = "2015-10-08 12:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dateString);
        bookings = repo.findAllBookingsByBookingDate(date);
        resultListNotEmpty();
    }

    @Test
    public void getAllBookingsByBookingDateListIsEmpty()throws Exception
    {
        String dateString = "2015-10-08 12:00:01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(dateString);
        System.out.println(date.toString());
        bookings = repo.findAllBookingsByBookingDate(date);
        assertTrue(bookings.size() == 0);
    }

    @Test
    public void getBookingByIdTest()throws Exception
    {
        Booking b = repo.findBookingById(1200);
        assertNotNull(b.getId());
    }

    private void resultListNotEmpty()
    {
        assertTrue(bookings.size()>0);
    }
}
