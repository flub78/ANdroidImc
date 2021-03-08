package flub78.org.imc;

import android.app.Application;
import android.util.Log;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by <VOTRE-NOM> on <DATE-DU-JOUR>.
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Config.OLDEST_SDK})
public class WeightsDAOTest extends TestCase {

    private final String TAG = "WeightsDAOTest";

    public WeightsDAO dao;


    @Before
    public void setUp() throws Exception {
        super.setUp();

        // uncomment to see the logs on stdout
        // ShadowLog.stream = System.out;

        Log.i(TAG, "setup (((");
        Application myapp = RuntimeEnvironment.application;
        dao = new WeightsDAO(myapp);
        assertNotNull(dao);
    }

    @After
    public void tearDown() throws Exception {
        Log.i(TAG, "))) teardown");
        dao.close();
    }

    private void addOne(WeightsDAO dao,
                        long id,
                        float weight,
                        float size,
                        String user,
                        String date,
                        String comment) {
        WeightRecord wr = new WeightRecord(id, user, weight,
                size,
                date,
                comment);

        assertNotNull(wr);
        dao.create(wr);
    }

    @Test
    public void create() {

        dao.open();

        int count = dao.count();

        assertEquals(0, count);

        this.addOne(dao, 42,
                97.0f,
                1.79f,
                "Me",
                "28/02/2021",
                "No comments");

        assertEquals(count + 1, dao.count());
        dao.close();
    }

    @Test
    public void delete() {
        dao.open();

        assertEquals(0, dao.count());

        this.addOne(dao, 42,
                97.0f,
                1.79f,
                "Me",
                "28/02/2021",
                "No comments");

        assertEquals(1, dao.count());

        this.addOne(dao, 43,
                77.0f,
                1.53f,
                "Someone",
                "28/02/2021",
                "No comments");

        assertEquals(2, dao.count());

        dao.delete(2);
        assertEquals(1, dao.count());
        dao.delete(1);
        assertEquals(0, dao.count());

        dao.close();
    }

    @Test
    public void update() {
        dao.open();

        int count = dao.count();

        assertEquals(0, count);

        this.addOne(dao, 42,
                97.0f,
                1.79f,
                "Me",
                "28/02/2021",
                "No comments");

        assertEquals(count + 1, dao.count());

        WeightRecord wr = new WeightRecord(1, "Joe",
                100.0f,
                2.0f,
                "2021-03-12",
                "no comment");

        dao.update(wr);
        assertEquals(count + 1, dao.count());

        wr = dao.select(1);
        assertEquals("Joe", wr.getUser());
        assertEquals(100.0f, wr.getWeight(), 0.001f);
        assertEquals(2.0f, wr.getSize(), 0.001f);

        dao.close();
    }

    @Test
    public void getAll() {
        dao.open();

        assertEquals(0, dao.count());

        this.addOne(dao, 42,
                97.0f,
                1.79f,
                "Me",
                "28/02/2021",
                "No comments");

        assertEquals(1, dao.count());

        this.addOne(dao, 43,
                77.0f,
                1.53f,
                "Someone",
                "28/02/2021",
                "No comments");

        assertEquals(2, dao.count());

        List<WeightRecord> l = dao.getAll();

        assertEquals(2, l.size());

        Log.d(TAG, l.get(0).toString());
        Log.d(TAG, l.get(1).toString());

        dao.close();
    }
}