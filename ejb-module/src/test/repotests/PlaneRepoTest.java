import com.realdolmen.course.domain.Plane;
import com.realdolmen.course.persistence.PlaneRepo;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SDOAX36 on 8/10/2015.
 */
public class PlaneRepoTest extends DataPersistenceTest {

    private PlaneRepo repo;
    private List<Plane>planes;


    @Before
    public void init()
    {
        repo = new PlaneRepo();
        repo.em = entityManager();
        planes = new ArrayList<>();
    }

    @Test
    public void getAllPlanesTest()throws Exception
    {
        planes = repo.getAllPlanes();
        resultListNotEmpty();
    }

    private void resultListNotEmpty()
    {
        assertTrue(planes.size()>0);
    }
}
