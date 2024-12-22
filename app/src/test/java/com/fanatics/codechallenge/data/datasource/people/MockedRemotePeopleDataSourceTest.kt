package com.fanatics.codechallenge.data.datasource.people

import com.fanatics.codechallenge.data.database.FakePeopleAPI
import com.fanatics.codechallenge.data.model.Person
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MockedRemotePeopleDataSourceTest {

    @MockK
    private lateinit var fakePeopleAPI: FakePeopleAPI

    private lateinit var objectUnderTest: RemotePeopleDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        objectUnderTest = MockedRemotePeopleDataSource(
            fakePeopleAPI = fakePeopleAPI
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `dataFlow - initial value is empty list`() = runTest {
        val result = objectUnderTest.dataFlow.first()

        Assert.assertTrue(
            "An initial list should be empty",
            result.isEmpty()
        )
    }

    @Test
    fun `refresh - ask API for people collection`() = runTest {
        val expected = listOf<Person>(mockk())
        coEvery { fakePeopleAPI.requestPeople() } returns expected

        objectUnderTest.refresh()

        val result = objectUnderTest.dataFlow.first()

        Assert.assertEquals(expected, result)
    }
}
