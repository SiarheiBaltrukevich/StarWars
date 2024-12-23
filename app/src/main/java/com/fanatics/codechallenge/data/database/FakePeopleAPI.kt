package com.fanatics.codechallenge.data.database

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.delay
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * That class was created in terms of providing the db, since the GraphQL schema is not working.
 */
@Singleton
class FakePeopleAPI @Inject constructor() {

    /**
     * Emulates a network work, uses some [delays]
     */
    suspend fun requestPeople(): List<Person> {
        delay(delays.random())
        return db
    }

    /**
     * Emulates a network work, uses some [delays]
     */
    suspend fun requestPerson(id: Long): Person? {
        delay(delays.random())
        return db.firstOrNull { it.id == id }
    }

    // to demonstrate onLoading work
    private val delays = listOf(100L, 500L, 750L)

    // to generate list of person
    private val names = listOf("Niall", "Jack", "Tom", "Donald", "Kate")
    private val secondNames = listOf("Crow", "Fox", "Smyth", "Hunter", "Fisher")
    private val homeWorlds = listOf("Rain Forest", "Green Fields", "Sand Desert", "River Side")
    private val species = listOf("Human", "Alien", "Android")
    private val genders = listOf("Male", "Female", "NoGender")
    private val db = generateBase()

    private fun generateBase(): List<Person> {
        val db = mutableListOf<Person>()

        for (i in 0..15) {
            db.add(
                Person(
                    id = i.toLong(),
                    name = "${names.random()} ${secondNames.random()}",
                    gender = genders.random(),
                    status = species.random(),
                    species = homeWorlds.random(),
                )
            )
        }
        return db
    }
}
