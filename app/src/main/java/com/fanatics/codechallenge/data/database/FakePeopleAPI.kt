package com.fanatics.codechallenge.data.database

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.delay
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakePeopleAPI @Inject constructor() {

    suspend fun requestPeople(): List<Person> {
        delay(1000)
        return db
    }

    suspend fun requestPerson(id: Long): Person? {
        delay(1000)
        return db.firstOrNull { it.id == id }
    }

    private val names = listOf("Niall", "Jack", "Tom", "Donald", "Kate")
    private val secondNames = listOf("Crow", "Fox", "Smyth", "Hunter", "Fisher")
    private val homeWorlds = listOf("Rain Forest", "Green Fields", "Sand Desert", "River Side")
    private val db = generateBase()

    private fun generateBase(): List<Person> {
        val db = mutableListOf<Person>()
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        for (i in 0..15) {
            db.add(
                Person(
                    id = i.toLong(),
                    name = "${names.random()} ${secondNames.random()}",
                    height = df.format(Random.nextDouble(1.5, 2.0)).toDouble(),
                    mass = df.format(Random.nextDouble(50.0, 100.0)).toDouble(),
                    homeworld = homeWorlds.random(),
                )
            )
        }
        return db
    }
}