package com.fanatics.codechallenge.data.datasource.person

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.fanatics.codeChanllenge.PersonQuery
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

class ApolloRemotePersonDataSourceTest {
    @MockK
    private lateinit var apolloClient: ApolloClient

    private lateinit var objectUnderTest: RemotePersonDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic("com.apollographql.apollo.coroutines.CoroutinesExtensionsKt")

        every { apolloClient.query(any<PersonQuery>()) } returns mockk {
            coEvery { this@mockk.await() } returns mockk {
                every { this@mockk.data } returns mockk {
                    every { this@mockk.character() } returns personResponse
                }
            }
        }

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

    @Test
    fun `request - ask API for people collection`() = runTest {
        val expected = Person(
            id = 1L,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male"
        )

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

    private val personResponse = PersonQuery.Character(
        "Character",
        "1",
        "Rick Sanchez",
        "Alive",
        "Human",
        "Male"
    )
}
