import com.realdolmen.course.domain.Plane;
import org.junit.Before;
import org.junit.Test;

public class PlanePersistenceTest extends DataPersistenceTest {
    private Plane plane;

    @Before
    public void init(){
        plane = new Plane();
        plane.setNumberOfSeats(50);
        plane.setPlaneCode("ZZZZ");
    }

    @Test
    public void planeCanBePersisted(){
        entityManager().persist(plane);
        assertEquals(plane.getPlaneCode(),entityManager().find(plane.getClass(), plane.getId()).getPlaneCode());
    }
}
