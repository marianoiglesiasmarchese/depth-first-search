package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FlightServiceTest(
    @Autowired val flightService: FlightService
) {

    @Test
    fun `should add the city 6`(){
        // Arrange
        val graph = arrayListOf<Node>()
        val numberOfCities = graph.size
        val origin = "5"
        val newCity = "6"

        // Act
        flightService.addNewFlight(graph, origin, newCity)

        // Assert
        Assertions.assertEquals(numberOfCities + 2, graph.size)
        Assertions.assertFalse(graph.none { it.name == newCity })
    }

    @Test
    fun `should add new flights between 5 and 6`(){
        // Arrange
        val graph = arrayListOf<Node>()
        val origin = "5"
        val destination = "7"

        // Act
        flightService.addNewFlight(graph, origin, destination)

        // Assert
        Assertions.assertNotNull(graph.find { it.name == origin })
        Assertions.assertNotNull(graph.find { it.name == destination })
        Assertions.assertNotNull(graph.find { it.name == origin }?.edges?.none { it.name == destination })
        Assertions.assertNotNull(graph.find { it.name == destination }?.edges?.none { it.name == origin })
        graph.find { it.name == origin }?.edges?.none { it.name == destination }?.let { Assertions.assertFalse(it) }
        graph.find { it.name == destination }?.edges?.none { it.name == origin }?.let { Assertions.assertFalse(it) }
    }

}