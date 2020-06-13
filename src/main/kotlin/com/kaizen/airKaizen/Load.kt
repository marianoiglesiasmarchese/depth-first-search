package com.kaizen.airKaizen

import com.kaizen.airKaizen.model.Node
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.BufferedReader
import java.io.FileReader
import java.util.*


@Configuration
class Load() {

    companion object {
        const val COMMA_DELIMITER = ","
    }

    val graph = arrayListOf<Node>()

    /**
     * Constraint
     * - All flights are bi-directional; AirKaizen serves to and from all cities
     * - AirKaizen uses the same identifier for a flight between two cities. So Chicago → Grand Rapids and Grand Rapids → Chicago both share flight id KZ01.
     */
    @Bean(name = ["graph"])
    fun loadData(): List<Node> {

        val records: MutableList<List<String>> = ArrayList()
        try {
            val file = FileReader(
                javaClass.classLoader.getResource("flights.csv").file
            )
            BufferedReader(file).use { br ->
                var line: String
                while (br.readLine().also { line = it } != null) {
                    val values = line.split(COMMA_DELIMITER).toTypedArray()
                    records.add(values.toList())
                    println(records)
                    process(values[0], values[1])
                }
            }
        } catch (e: Exception){
            println(e.stackTrace)
        }

        return graph
    }

    private fun process(origin: String, destination: String) {
        var originNode : Node = graph.find { it.name == origin } ?: Node(name = origin)
        var destinationNode : Node = graph.find { it.name == destination } ?: Node(name = destination)
        // check if one of them is new and create it
        if (graph.none { it.name == origin }){
            graph.add(originNode)
        }
        if (graph.none { it.name == destination }){
            graph.add(destinationNode)
        }
        // add the relationship
        if (originNode.edges.none { it.name == destinationNode.name }){
            originNode.edges.add(destinationNode)
        }
        if (destinationNode.edges.none { it.name == originNode.name }){
            destinationNode.edges.add(originNode)
        }
    }

}
