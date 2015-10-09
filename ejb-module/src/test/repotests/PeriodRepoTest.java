import com.realdolmen.course.domain.Period;
import com.realdolmen.course.persistence.PeriodRepo;
import com.realdolmen.course.utils.DateUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SDOAX36 on 8/10/2015.
 */
public class PeriodRepoTest extends DataPersistenceTest {

    private PeriodRepo repo;
    private List<Period> periods;

    @Before
    public void init()
    {
        repo = new PeriodRepo();
        repo.em = entityManager();
        periods = new ArrayList<>();
    }

    @Test
    public void getAllPeriodsTest() throws Exception
    {
        periods = repo.getAllPeriods();
        returnListNotEmpty();

    }

    @Test
    public void getAllPeriodesFromArrivalDate()throws Exception
    {
        Date date = DateUtil.stringToDate("2015-10-19", "yyyy-MM-dd");
        periods = repo.getAllPeriodsFromArrivalDate(date);
        returnListNotEmpty();
    }

    @Test
    public void getAllPeriodsFromDepartureDate()throws Exception
    {
        Date date = DateUtil.stringToDate("2015-10-15", "yyyy-MM-dd");
        periods = repo.getAllPeriodsFromDepartureDate(date);
        returnListNotEmpty();
    }

    private void returnListNotEmpty()
    {
        assertTrue(periods.size()>0);
    }

}
