package com.fanatics.codechallenge.data.repository

import com.fanatics.codechallenge.data.datasource.people.RemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.person.RemotePersonDataSource
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.utils.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PeopleRepositoryTest {

    @Rule
    @JvmField
    val coroutineRule: TestCoroutineRule = TestCoroutineRule()

    @MockK
    private lateinit var remotePeopleDataSource: RemotePeopleDataSource
    @MockK
    private lateinit var remotePersonDataSource: RemotePersonDataSource

    private lateinit var objectUnderTest: PeopleRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        objectUnderTest = PeopleRepository(
            remotePeopleDataSource = remotePeopleDataSource,
            remotePersonDataSource = remotePersonDataSource,
            dispatcherProvider = coroutineRule.dispatchersProvider,
            coroutineScope = coroutineRule.coroutineScope,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `chosenPersonFlow - calls remotePersonSource`() {
        val expected: Flow<Person?> = flowOf(null)
        every { remotePersonDataSource.dataFlow } returns expected

        val result = objectUnderTest.personFlow

        verify { remotePersonDataSource.dataFlow }

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `peopleFlow - not empty list, calls remotePeopleSource, should not call refresh`() = runTest {
        val expected = mockk<Person>()
        val flow: Flow<List<Person>> = flowOf(listOf(expected))
        every { remotePeopleDataSource.dataFlow } returns flow

        val result = objectUnderTest.peopleFlow.first().first()

        verify { remotePeopleDataSource.dataFlow }

        Assert.assertEquals(expected, result)
    }

    @Test
    fun `peopleFlow - empty list, calls remotePeopleSource, should call refresh`() = runTest {
        val flow: Flow<List<Person>> = flowOf(emptyList())
        every { remotePeopleDataSource.dataFlow } returns flow
        coEvery { remotePeopleDataSource.refresh() } returns Unit

        val result = objectUnderTest.peopleFlow.first()

        verify { remotePeopleDataSource.dataFlow }
        coVerify { remotePeopleDataSource.refresh() }

        Assert.assertTrue(
            "The list should be empty",
            result.isEmpty()
        )
    }

    @Test
    fun `chosePerson - calls remotePersonDataSource`() {
        val personId = 1L
        coEvery { remotePersonDataSource.request(personId) } returns Unit

        objectUnderTest.chosePerson(personId)

        coVerify { remotePersonDataSource.request(personId) }
    }

    @Test
    fun `clearPerson - calls remotePersonDataSource`() {
        every { remotePersonDataSource.clear() } returns Unit

        objectUnderTest.clearPerson()

        verify { remotePersonDataSource.clear() }
    }

    @Test
    fun `refreshPeople - calls remotePeopleDataSource`() {
        coEvery { remotePeopleDataSource.refresh() } returns Unit

        objectUnderTest.refreshPeople()

        coVerify { remotePeopleDataSource.refresh() }
    }
}
