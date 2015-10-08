import com.realdolmen.course.domain.Country;
import com.realdolmen.course.persistence.CountryRepo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 8/10/2015.
 */
public class CountryRepoTest extends DataPersistenceTest {


    private CountryRepo repo;
    private List<Country>countries;

    @Before
    public void init()
    {
        repo = new CountryRepo();
        repo.em = entityManager();
        countries = new ArrayList<>();
    }


    @Test
    public void getAllCountriesTest()throws Exception
    {

        countries = repo.getAllCountries();
        resultListNotEmpty();
    }

    @Test
    public void getAllCountriesByPartOfName()throws Exception
    {
        countries = repo.getCountriesByName("Bel");
        //Belgium, Belize, Belarus
        resultListNotEmpty();
        assertEquals(3, countries.size());
    }

    @Test
    public void getAllCountriesByNameTestOnly1() throws Exception
    {
        countries = repo.getCountriesByName("Belgium");
        resultListNotEmpty();
        assertEquals(1,countries.size());
    }

    private void resultListNotEmpty()
    {
        assertTrue(countries.size()>0);
    }


}
