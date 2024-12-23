package com.fanatics.codechallenge.data.datasource.people

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.fanatics.codeChanllenge.PeopleQuery
import com.fanatics.codechallenge.data.model.Person
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
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
        mockkStatic("com.apollographql.apollo.coroutines.CoroutinesExtensionsKt")

        every { apolloClient.query(any<PeopleQuery>()) } returns mockk {
            coEvery { this@mockk.await() } returns mockk {
                every { this@mockk.data } returns mockk {
                    every { this@mockk.characters() } returns mockk {
                        every { this@mockk.results() } returns listOf(personResponse)
                    }
                }
            }
        }

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

    @Test
    fun `refresh - ask API for people collection`() = runTest {
        val expected = Person(
            id = 1L,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male"
        )

        objectUnderTest.refresh()

        val result = objectUnderTest.dataFlow.first()

        Assert.assertEquals(expected, result.first())
    }

    private val personResponse = PeopleQuery.Result(
        "Character",
        "1",
        "Rick Sanchez",
        "Alive",
        "Human",
        "Male"
    )
}
