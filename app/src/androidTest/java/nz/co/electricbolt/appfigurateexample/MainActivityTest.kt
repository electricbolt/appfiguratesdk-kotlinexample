// MainActivityTest.java
// AppfigurateExample Copyright © 2023-2025; Electric Bolt Limited.

package nz.co.electricbolt.appfigurateexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import nz.co.electricbolt.appfigurateexample.ExampleConfiguration.Companion.CONFIGURATION
import nz.co.electricbolt.appfigurateexample.RecyclerViewMatcher.atPosition
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Set Build Variant to "debug" before attempting to run automation tests.

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        CONFIGURATION().automationReset()
    }

    @Test
    fun test_1_Reset() {
        onView(withId(R.id.recycler_view)).check(matches(atPosition(0, hasDescendant(withText("true")))))
    }

    @Test
    fun test_2_BooleanValue() {
        onView(withId(R.id.recycler_view)).check(matches(atPosition(0, hasDescendant(withText("true")))))
        CONFIGURATION().bool = false
        CONFIGURATION().automationApply()
        onView(withId(R.id.recycler_view)).check(matches(atPosition(0, hasDescendant(withText("false")))))
    }

    @Test
    fun test_3_String_Textfield_Value() {
        onView(withId(R.id.recycler_view)).check(matches(atPosition(1, hasDescendant(withText("tuesday")))))
        CONFIGURATION().string_Textfield = "thursday"
        CONFIGURATION().automationApply()
        onView(withId(R.id.recycler_view)).check(matches(atPosition(1, hasDescendant(withText("thursday")))))
    }

    @Test
    fun test_4_Action() {
        onView(withId(R.id.recycler_view)).check(matches(atPosition(8, hasDescendant(withText("500")))))
        CONFIGURATION().automationAction("randomIntegers")
        onView(withId(R.id.recycler_view)).check(matches(atPosition(8, hasDescendant(Matchers.not(withText("500"))))))
    }
}