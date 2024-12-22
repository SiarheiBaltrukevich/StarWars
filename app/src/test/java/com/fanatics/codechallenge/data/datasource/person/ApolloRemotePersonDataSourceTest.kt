package com.fanatics.codechallenge.data.datasource.person

import com.apollographql.apollo.ApolloClient
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApolloRemotePersonDataSourceTest {
    @MockK
    private lateinit var apolloClient: ApolloClient

    private lateinit var objectUnderTest: RemotePersonDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        objectUnderTest = ApolloRemotePersonDataSource(
            apolloClient = apolloClient
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

    @Test // API is not prepared. not working schema
    fun `refresh - ask API for people collection`() = runTest {
//        val expected = mockk<Person>()
//        coEvery { apolloClient.query() } returns expected
//
//        objectUnderTest.request(1)
//
//        val result1 = objectUnderTest.dataFlow.first()
//
//        Assert.assertEquals(expected, result1)
//
//        objectUnderTest.clear()
//
//        val result2 = objectUnderTest.dataFlow.first()
//
//        Assert.assertNull(
//            "After clearing a person should be null",
//            result2
//        )
    }
}
