package flub78.org.imc;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    // Existing test inthe example
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    // Test to experiment with mockito framework
    @Test
    public void testWalletContent() throws Exception {

        //AuthActivity authActivity = new AuthActivity();
        AuthActivity authActivity = mock(AuthActivity.class);
        when(authActivity.getUserToken()).thenReturn("FakeToken");

        String token = authActivity.getUserToken();
        Log.d("ExampleUnitTest", "token = "+ token);
        System.out.println("ExampleUnitTest " + "token = "+ token);

        assertNotNull(authActivity);

        // Test token
    }
}