package io.estevanfick.desafioandroid.ui.repolist

import android.content.Context
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import io.estevanfick.desafioandroid.R.id.recyclerRepository
import io.estevanfick.desafioandroid.data.api.ApiManager
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException








class RepoActivityTest{

    @Rule @JvmField
    val activity = ActivityTestRule<RepoActivity>(RepoActivity::class.java, true, false)

    val server = MockWebServer()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server.start()
        ApiManager.url = server.url("/").toString()
    }


    @Test
    fun showSomeRepos(){
        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getFile(getInstrumentation().context)))
        activity.launchActivity(null)

        Thread.sleep(1_000)
        onView(withId(recyclerRepository))
                .check(matches(isDisplayed()))
        onView(withText("kotlin"))
                .check(matches(isDisplayed()))

    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        server.shutdown()
    }

    fun getFile(context: Context): String{
        val file = context.assets.open("kotlin_search.json")
        return file.bufferedReader().use { it.readText() }
    }





}