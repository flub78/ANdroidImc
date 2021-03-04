package flub78.org.imc;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
public class WeightsDAOTest {

    Context context = mock(Context.class);
    public WeightsDAO dao = new WeightsDAO(context);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {

        int count = dao.count();

        assertEquals(0, count);

        WeightRecord wr = new WeightRecord(42,
                97.0f,
                1.79f,
                "Me",
                "28/02/2021",
                "No comments");

        assertNotNull(wr);
        dao.create(wr);

        // assertEquals(count + 1, dao.count());
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getAll() {
    }
}