package com.cuvelo.shopfully.test

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cuvelo.shopfully.test.ui.main.MainActivity
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get : Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    //private lateinit var scenario : ActivityScenario<MainActivity>

//    @Before
//    fun setup(){
//        scenario = mActivityRule.scenario
//    }

    @Test
    fun checkIfFilterIsEnabledWhenAppStarts() {

      //  scenario.moveToState(Lifecycle.State.CREATED)

        onView(ViewMatchers.withId(R.id.iv_filter_icon))
            .check(ViewAssertions.matches(ViewMatchers.isNotSelected()))

    }

    @After
    fun tearDown() {
        //clean up code
    }


}