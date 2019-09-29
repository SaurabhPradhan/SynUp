package com.synup.sample;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.synup.sample.model.variantservice.APIServiceProvider;
import com.synup.sample.ui.activity.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


public class RetryButtonTest extends AndroidJUnitRunner {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
    }

    private ViewAction setButtonViewVisibility(final boolean isVisible) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(Button.class);
            }

            @Override
            public String getDescription() {
                return "Show/ Hide View";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.setVisibility(isVisible ? View.VISIBLE :View.INVISIBLE);
            }
        };
    }

    private ViewAction setProgressViewVisibility() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(ProgressBar.class);
            }

            @Override
            public String getDescription() {
                return "Show/ Hide View";
            }

            @Override
            public void perform(UiController uiController, View view) {
                view.setVisibility(View.INVISIBLE);
            }
        };
    }


    @Test
    public void testRetryButtonIsShown() throws Exception {
        String fileName = "variant_404_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), fileName)));
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        WifiManager wifi=(WifiManager)mActivityRule.getActivity().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
        onView(withId(R.id.retry)).perform(setButtonViewVisibility(true));
        onView(withId(R.id.progress_circular)).perform(setProgressViewVisibility());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.retry)).check(matches(isDisplayed()));
        onView(withId(R.id.retry)).perform(click());
        onView(withText(R.string.turn_on_wifi)).inRoot(RootMatchers.withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testRetryButtonNotShown() throws Exception {
        String fileName = "variant_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(InstrumentationRegistry.getInstrumentation().getContext(), fileName)));
        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);
        onView(withId(R.id.retry)).perform(setButtonViewVisibility(false));
        WifiManager wifi=(WifiManager)mActivityRule.getActivity().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
        SystemClock.sleep(10000);
        APIServiceProvider.getInstance().requestData();
        SystemClock.sleep(5000);
        onView(withId(R.id.retry)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
        mActivityRule.finishActivity();
    }

}
