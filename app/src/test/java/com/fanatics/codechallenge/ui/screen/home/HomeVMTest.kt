package com.fanatics.codechallenge.ui.screen.home

import android.content.res.Resources
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
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeVMTest {

    @Rule
    @JvmField
    val coroutineRule = TestCoroutineRule()

    @MockK
    lateinit var peopleRepository: PeopleRepository
    @MockK
    lateinit var resources: Resources

    private val peopleFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())

    private lateinit var objectUnderTest: HomeVM

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { resources.getString(any()) } answers { args[0].toString() }
        every { peopleRepository.peopleFlow } returns peopleFlow

        objectUnderTest = HomeVM(
            peopleRepository = peopleRepository,
            dispatcherProvider = coroutineRule.dispatchersProvider,
            resources = resources,
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

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePeople - empty list, not timed out - emit Loading`() = runTest {
        val expected = HomeUIState.Loading

        objectUnderTest.handleUIAction(UIAction.ObservePeople)

        val actual = objectUnderTest.uiState.first()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePeople - not empty list, not timed out - emit Success`() = runTest {
        val person = mockk<Person>()
        val expected = HomeUIState.Success(listOf(person))

        peopleFlow.value = listOf(person)

        objectUnderTest.handleUIAction(UIAction.ObservePeople)

        val actual = objectUnderTest.uiState.first()

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `handle action - RefreshPeople - call repository - emit Loading`() = runTest {
        val expected = HomeUIState.Loading

        every { peopleRepository.refreshPeople() } returns Unit

        objectUnderTest.handleUIAction(UIAction.RefreshPeople)

        val actual = objectUnderTest.uiState.first()

        verify { peopleRepository.refreshPeople() }

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ChosePerson - call repository - emit Loading`() = runTest {
        val personId = 1L

        every { peopleRepository.chosePerson(personId) } returns Unit

        objectUnderTest.handleUIAction(UIAction.ChosePerson(personId))

        verify { peopleRepository.chosePerson(personId) }
    }
}
