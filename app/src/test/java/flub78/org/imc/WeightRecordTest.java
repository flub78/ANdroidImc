package flub78.org.imc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by flub78 on 2021-03-04.
 */
public class WeightRecordTest {

    WeightRecord mWR;

    @Before
    public void setUp() throws Exception {
        mWR = new WeightRecord(42, "Me", 97.0f, 1.79f,  "28/02/2021", "No comments");
    }

    @After
    public void tearDown() throws Exception {
        mWR = null;
    }

    @Test
    public void getId() {
        assertEquals(42, mWR.getId());
    }

    @Test
    public void setId() {
    }

    @Test
    public void getWeight() {
        assertEquals(97.0f, mWR.getWeight(), 0.0000001f);
    }

    @Test
    public void setWeight() {
    }

    @Test
    public void getSize() {
        assertEquals(1.79f, mWR.getSize(), 0.0000001f);
    }

    @Test
    public void setSize() {
        mWR.setSize(2.0f);
        assertEquals(1.999f, mWR.getSize(), 0.1f);
    }

    @Test
    public void getUser() {
        assertEquals("Me", mWR.getUser());
    }

    @Test
    public void setUser() {
    }

    @Test
    public void getDate() {
    }

    @Test
    public void setDate() {
    }

    @Test
    public void getComment() {
        assertEquals("No comments", mWR.getComment());
    }

    @Test
    public void setComment() {
    }
}