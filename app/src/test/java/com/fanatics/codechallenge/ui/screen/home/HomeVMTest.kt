package com.fanatics.codechallenge.ui.screen.home

import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.data.repository.PeopleRepository
import com.fanatics.codechallenge.utils.TestCoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeVMTest {

    @Rule
    @JvmField
    val coroutineRule = TestCoroutineRule()

    @MockK
    lateinit var peopleRepository: PeopleRepository

    private val peopleFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())

    private lateinit var objectUnderTest: HomeVM

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { peopleRepository.peopleFlow } returns peopleFlow

        objectUnderTest = HomeVM(
            peopleRepository = peopleRepository,
            dispatcherProvider = coroutineRule.dispatchersProvider,
        )
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `initial state is Loading`() = runTest {
        val expected = HomeUIState.Loading
        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePeople - empty list - emit Loading`() = runTest {
        val expected = HomeUIState.Loading

        objectUnderTest.handleUIAction(UIAction.ObservePeople)

        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePeople - not empty list - emit Success`() = runTest {
        val person = mockk<Person>()
        val expected = HomeUIState.Success(listOf(person))

        peopleFlow.value = listOf(person)

        objectUnderTest.handleUIAction(UIAction.ObservePeople)

        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePeople - error - emit Error`() = runTest {
        val expectedMessage = "Test error"
        val error = Throwable(expectedMessage)
        val expected = HomeUIState.Error(error.localizedMessage.orEmpty())

        every { peopleRepository.peopleFlow } returns flow { throw error }

        objectUnderTest.handleUIAction(UIAction.ObservePeople)

        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - RefreshPeople - call repository - emit Loading`() = runTest {
        val expected = HomeUIState.Loading

        every { peopleRepository.refreshPeople() } returns Unit

        objectUnderTest.handleUIAction(UIAction.RefreshPeople)

        val actual = objectUnderTest.uiState.first()

        verify { peopleRepository.refreshPeople() }

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ChosePerson - call repository - emit Loading`() = runTest {
        val personId = 1L

        every { peopleRepository.chosePerson(personId) } returns Unit

        objectUnderTest.handleUIAction(UIAction.ChosePerson(personId))

        verify { peopleRepository.chosePerson(personId) }
    }
}
