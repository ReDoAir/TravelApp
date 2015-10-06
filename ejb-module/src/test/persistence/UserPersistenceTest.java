import com.realdolmen.course.domain.auth.Role;
import com.realdolmen.course.domain.auth.User;
import org.junit.Test;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

/**
 * Created by SDOAX36 on 6/10/2015.
 */
public class UserPersistenceTest extends DataPersistenceTest{

    @Test
    public void canPersistUser()throws Exception
    {
        User user = new User("gekkerd123","secret",Role.ADMIN);
        entityManager().persist(user);
        assertNotNull(user.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void canNotPersisteUserWithoutUsername() throws Exception
    {
        User user = new User();
        user.setPassword("supersecret");
        user.addRole(Role.ADMIN);
        entityManager().persist(user);
        assertNull(user.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void canNotPersistUserWithoutPassword()throws Exception
    {
        User user = new User();
        user.setUsername("yoMama");
        user.addRole(Role.ADMIN);
        entityManager().persist(user);
        assertNull(user.getId());
    }

    @Test(expected = PersistenceException.class)
    public void canNotPersistUserWhenUsernameExists()throws Exception
    {
        User user = new User("steven","secret",Role.ADMIN);
        entityManager().persist(user);
        assertNull(user.getId());

    }

    @Test
    public void canFindUserById() throws Exception
    {
        assertEquals("steven",entityManager().find(User.class,1000L).getUsername());

    }


}
