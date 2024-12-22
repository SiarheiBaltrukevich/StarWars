package com.fanatics.codechallenge.ui.screen.person

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
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PersonVMTest {
    @Rule
    @JvmField
    val coroutineRule = TestCoroutineRule()

    @MockK
    lateinit var peopleRepository: PeopleRepository
    @MockK
    lateinit var resources: Resources

    private val personFlow: MutableStateFlow<Person?> = MutableStateFlow(null)

    private lateinit var objectUnderTest: PersonVM

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { resources.getString(any()) } answers { args[0].toString() }
        every { peopleRepository.personFlow } returns personFlow

        objectUnderTest = PersonVM(
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
        val expected = PersonUIState.Loading
        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePerson - null, not timed out - emit Loading`() = runTest {
        val expected = PersonUIState.Loading

        objectUnderTest.handleUIAction(UIAction.ObservePerson)

        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - ObservePerson - not null, not timed out - emit Success`() = runTest {
        val person = mockk<Person>()
        val expected = PersonUIState.Success(person)

        personFlow.value = person

        objectUnderTest.handleUIAction(UIAction.ObservePerson)

        val actual = objectUnderTest.uiState.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `handle action - BackToHome - call repository`() = runTest {
        every { peopleRepository.clearPerson() } returns Unit

        objectUnderTest.handleUIAction(UIAction.BackToHome)

        verify { peopleRepository.clearPerson() }
    }
}
