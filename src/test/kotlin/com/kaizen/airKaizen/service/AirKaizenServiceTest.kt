package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AirKaizenServiceTest(
    @Qualifier("graph") val graph: List<Node>,
    @Autowired val airKaizenService: AirKaizenService
) {

    // show all the possible options to travel
    @ExperimentalStdlibApi
    @Test
    fun `should retrieve some paths`(){
        // define origin and destination, I assume that they will exist after the search
        val destination = graph.find { it.name == "5" }
        val origin = graph.find { it.name == "1" }

        airKaizenService.findPaths(origin!!, destination!!)
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