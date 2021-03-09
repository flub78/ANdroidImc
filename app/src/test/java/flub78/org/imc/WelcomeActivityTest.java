package flub78.org.imc;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import flub78.org.imc.controller.ImcActivity;
import flub78.org.imc.controller.WelcomeActivity;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by flub78 on 2021-03-09.
 *
 * TODO Check that the button is disabled when the user name is empty
 * TODO check that the button is clickable when the user name is not empty
 * TODO check that the user name is persistent
 */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Config.OLDEST_SDK})
public class WelcomeActivityTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void clickingLogin_shouldStartImcActivity() {
        WelcomeActivity activity = Robolectric.setupActivity(WelcomeActivity.class);
        activity.findViewById(R.id.login).performClick();

        Intent expectedIntent = new Intent(activity, ImcActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());
    }
}