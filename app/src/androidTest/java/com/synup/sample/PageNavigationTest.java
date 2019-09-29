package com.synup.sample;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.AndroidJUnitRunner;

import com.synup.sample.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PageNavigationTest extends AndroidJUnitRunner {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void pagerSwipeTest() throws Exception{
        onView(withId(R.id.viewpager)).perform(ViewActions.swipeLeft());
        Thread.sleep(2000);
        onView(withId(R.id.viewpager)).perform(ViewActions.swipeRight());
    }
}

