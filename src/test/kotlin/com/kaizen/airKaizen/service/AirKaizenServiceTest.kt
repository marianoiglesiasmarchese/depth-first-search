package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert

@SpringBootTest
class AirKaizenServiceTest(
    @Qualifier("graph") val graph: List<Node>,
    @Autowired val airKaizenService: AirKaizenService
) {

    // show all the possible options to travel
    @ExperimentalStdlibApi
    @Test
    fun `should retrieve some paths`(){
        // Arrange
        // define origin and destination, I assume that they will exist after the search
        val destination = graph.find { it.name == "5" }
        val origin = graph.find { it.name == "1" }

        // Act
        val paths = airKaizenService.findPaths(origin!!, destination!!)

        // Assert
        Assertions.assertEquals(10,paths.size)
    }

    // TODO list all the cities
    @Disabled
    @Test
    fun `should list all the cities`(){

    }

    // TODO add new flights (edges)
    @Disabled
    @Test
    fun `should add new flights`(){

    }

}