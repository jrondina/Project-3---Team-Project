package com.jonathan.james.eric.project_3;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by erickivet on 8/19/16.
 */

@RunWith(AndroidJUnit4.class)
public class SectionPageAdapterTest {

    @Rule
    public ActivityTestRule<SectionPageAdapterActivity> sPageAdapterTestRule =
            new ActivityTestRule<SectionPageAdapterActivity>(SectionPageAdapterActivity.class);

    @Test
    public void testSwipes() throws Exception {
        onView(withId(R.id.section_fragment_container)).perform(swipeDown());
        onView(withId(R.id.section_fragment_container)).perform(swipeUp());
        onView(withId(R.id.section_fragment_container)).perform(swipeLeft());
        onView(withId(R.id.section_fragment_container)).perform(swipeDown());
        onView(withId(R.id.section_fragment_container)).perform(swipeUp());
        onView(withId(R.id.section_fragment_container)).perform(swipeRight());
        onView(withId(R.id.section_fragment_container)).perform(click());
    }

    @Test
    public void testclick() throws Exception {

        onView(withId(R.id.section_fragment_container))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1,click()));

        onView(withId(R.id.section_fragment_container))
                .perform(RecyclerViewActions.scrollToPosition(4));

        onView(withId(R.id.section_fragment_container))
                .perform(click());

    }


}
