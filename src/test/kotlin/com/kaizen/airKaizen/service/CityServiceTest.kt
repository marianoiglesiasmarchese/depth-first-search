package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CityServiceTest(
    @Autowired val flightService: FlightService,
    @Autowired val cityService: CityService
) {

    @Test
    fun `should retrieve all the cities`(){
        // Arrange
        val graph = arrayListOf<Node>()
        val cityNames = listOf("1", "2", "3", "4")
        val cities = listOf(
            "1" to "2",
            "2" to "3",
            "2" to "4",
            "2" to "1"
        )
        cities.forEach {
            flightService.addNewFlight(graph, it.first, it.second )
        }

        // Act
        val result = cityService.cities(graph)

        // Assert
        Assertions.assertEquals(graph.size, result.size)
        result.forEach { Assertions.assertTrue(cityNames.contains(it)) }
    }

}