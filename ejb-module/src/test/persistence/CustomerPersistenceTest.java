import com.realdolmen.course.domain.auth.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * Created by SDOAX36 on 6/10/2015.
 */
public class CustomerPersistenceTest extends DataPersistenceTest{

    private Customer customer1;
    //we need him to fail!!!
    private Customer customer2;

    @Before
    public void init() throws Exception
    {
        customer1 = new Customer("gekkerd","secret","555d55d5d5555d5");
        customer2 = new Customer();
    }

    @Test
    public void canPersistCustomerTest()throws Exception
    {

        entityManager().persist(customer1);
        assertNotNull(customer1.getId());

    }

    @Test(expected = ConstraintViolationException.class)
    public void canNotPersistWithoutUsername() throws Exception
    {

        customer2.setPassword("secret");
        customer2.setCreditCard("mlskqdjfqsmdmflj");
        entityManager().persist(customer2);
        assertNull(customer2.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void canNotPersistWithoutPassword() throws Exception
    {
        customer2.setUsername("secretMotherFucker");
        customer2.setCreditCard("mlskdjfmlkjdqf");

        entityManager().persist(customer2);
        assertNull(customer2.getId());
    }

    @Test(expected = PersistenceException.class)
    public void canNotPersistWhenUsernameExists() throws Exception
    {
        customer2.setUsername("gekkerd");
        customer2.setPassword("supersecretmotherfucker");
        entityManager().persist(customer1);
        entityManager().persist(customer2);
        assertNull(customer2.getId());
    }

    @Test
    public void canFindCustomerById() throws Exception
    {
        assertEquals("customer1",entityManager().find(Customer.class,1300).getUsername());
    }
}
