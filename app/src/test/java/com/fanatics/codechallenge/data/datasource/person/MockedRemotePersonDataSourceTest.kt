package com.fanatics.codechallenge.data.datasource.person

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

class MockedRemotePersonDataSourceTest {
    @MockK
    private lateinit var fakePeopleAPI: FakePeopleAPI

    private lateinit var objectUnderTest: RemotePersonDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        objectUnderTest = MockedRemotePersonDataSource(
            fakePeopleAPI = fakePeopleAPI
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `dataFlow - initial value is null`() = runTest {
        val result = objectUnderTest.dataFlow.first()

        Assert.assertNull(
            "An initial list should be null",
            result
        )
    }

    @Test
    fun `refresh - ask API for people collection`() = runTest {
        val expected = mockk<Person>()
        coEvery { fakePeopleAPI.requestPerson(any()) } returns expected

        objectUnderTest.request(1)

        val result1 = objectUnderTest.dataFlow.first()

        Assert.assertEquals(expected, result1)

        objectUnderTest.clear()

        val result2 = objectUnderTest.dataFlow.first()

        Assert.assertNull(
            "After clearing a person should be null",
            result2
        )
    }
}
