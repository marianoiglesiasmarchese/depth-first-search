package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RouteServiceTest(
    @Qualifier("graph") val graph: ArrayList<Node>,
    @Autowired val routeService: RouteService
) {

    @ExperimentalStdlibApi
    @Test
    fun `should retrieve all the paths`(){
        // Arrange, I'm assuming that they will exist after the search
        val destination = graph.find { it.name == "5" }
        val origin = graph.find { it.name == "1" }

        // Act
        val paths = routeService.findPaths(origin!!, destination!!)

        // print path
        println("number of paths: ${paths.size}")
        paths.forEach {
            print("path: ")
            it.forEach { each -> print("${each.name}," ) }
            println()
        }

        // Assert
        Assertions.assertEquals(10, paths.size)
    }

    @ExperimentalStdlibApi
    @Test
    fun `should retrieve paths with size less than 4`(){
        // Arrange, I'm assuming that they will exist after the search
        val destination = graph.find { it.name == "5" }
        val origin = graph.find { it.name == "1" }

        // Act
        val paths = routeService.findPaths(origin!!, destination!!)

        // print path
        println("number of paths: ${paths.size}")
        paths.forEach {
            print("path: ")
            it.forEach { each -> print("${each.name}," ) }
            println()
        }

        // Assert
        paths.forEach {
            Assertions.assertTrue(it.size <= 4)
        }
    }

    @ExperimentalStdlibApi
    @Test
    fun `should contain origin and destination as part of the path`(){
        // Arrange, I'm assuming that they will exist after the search
        val destination = graph.find { it.name == "5" }
        val origin = graph.find { it.name == "1" }

        // Act
        val paths = routeService.findPaths(origin!!, destination!!)

        // print path
        println("number of paths: ${paths.size}")
        paths.forEach {
            print("path: ")
            it.forEach { each -> print("${each.name}," ) }
            println()
        }

        // Assert
        paths.forEach {
            Assertions.assertTrue(it.contains(origin))
            Assertions.assertTrue(it.contains(destination))
        }
    }

    @ExperimentalStdlibApi
    @Test
    fun `a city should not be repeated inner the same path`(){
        // Arrange, I'm assuming that they will exist after the search
        val destination = graph.find { it.name == "5" }
        val origin = graph.find { it.name == "1" }

        // Act
        val paths = routeService.findPaths(origin!!, destination!!)

        // print path
        println("number of paths: ${paths.size}")
        paths.forEach {
            print("path: ")
            it.forEach { each -> print("${each.name}," ) }
            println()
        }

        // Assert
        paths.forEach {
            val cities = hashSetOf<String>()
            it.forEach { node ->
                cities.add(node.name)
            }
            Assertions.assertEquals(it.size, cities.size)
        }
    }

}