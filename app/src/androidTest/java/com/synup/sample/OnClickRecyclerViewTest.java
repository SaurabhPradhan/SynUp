package com.synup.sample;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import com.synup.sample.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

public class OnClickRecyclerViewTest extends AndroidJUnitRunner {

    @Rule
    public final ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recyclerOnClickTest() throws Exception  {
        Thread.sleep(2000);
        if (getRecyclerViewCount() > 0){
            onView(allOf(isDisplayed(), withId(R.id.recycler_id)))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
            Thread.sleep(1000);
            onView(allOf(isDisplayed(), withId(R.id.recycler_id)))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
            Thread.sleep(1000);
            onView(allOf(isDisplayed(), withId(R.id.recycler_id)))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        }

    }

    private int getRecyclerViewCount(){
        RecyclerView recyclerView = mActivityRule.getActivity().findViewById(R.id.recycler_id);
        return Objects.requireNonNull(recyclerView.getAdapter()).getItemCount();
    }
}
