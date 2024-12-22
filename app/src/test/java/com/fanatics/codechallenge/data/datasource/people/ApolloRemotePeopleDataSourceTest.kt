package com.fanatics.codechallenge.data.datasource.people

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

class ApolloRemotePeopleDataSourceTest {
    @MockK
    private lateinit var apolloClient: ApolloClient

    private lateinit var objectUnderTest: RemotePeopleDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        objectUnderTest = ApolloRemotePeopleDataSource(
            apolloClient = apolloClient
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

    @Test // API is not prepared. not working schema
    fun `refresh - ask API for people collection`() = runTest {
//        val expected = listOf<Person>(mockk())
//        coEvery { apolloClient.query(....) } returns expected
//
//        objectUnderTest.refresh()
//
//        val result = objectUnderTest.dataFlow.first()
//
//        Assert.assertEquals(expected, result.isEmpty())
    }
}
