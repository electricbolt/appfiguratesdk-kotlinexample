// MainActivityTest.java
// AppfigurateExample Copyright © 2023; Electric Bolt Limited.
package nz.co.electricbolt.appfigurateexample

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import nz.co.electricbolt.appfiguratelibrary.Configuration
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    private var config: ExampleConfiguration? = null

    @JvmField
    @Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        config = Configuration.sharedConfiguration() as ExampleConfiguration
        config?.automationReset()
    }

    @Test
    fun test_1_Reset() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("true")))))
    }

    @Test
    fun test_2_BooleanValue() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("true")))))
        config?.bool = false
        config?.automationApply()
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(0, ViewMatchers.hasDescendant(ViewMatchers.withText("false")))))
    }

    @Test
    fun test_3_String_Textfield_Value() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(1, ViewMatchers.hasDescendant(ViewMatchers.withText("tuesday")))))
        config?.string_Textfield = "thursday"
        config?.automationApply()
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(1, ViewMatchers.hasDescendant(ViewMatchers.withText("thursday")))))
    }

    @Test
    fun test_4_Action() {
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(8, ViewMatchers.hasDescendant(ViewMatchers.withText("500")))))
        config?.automationAction("randomIntegers")
        Espresso.onView(ViewMatchers.withId(R.id.recycler_view)).check(ViewAssertions.matches(RecyclerViewMatcher.atPosition(8, ViewMatchers.hasDescendant(Matchers.not(ViewMatchers.withText("500"))))))
    }
}