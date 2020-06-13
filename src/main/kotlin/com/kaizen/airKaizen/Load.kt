package com.kaizen.airKaizen

import com.kaizen.airKaizen.model.Node
import com.kaizen.airKaizen.service.FlightService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.BufferedReader
import java.io.FileReader
import java.util.*


@Configuration
class Load(
    val flightService: FlightService
) {

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
    fun loadData(): ArrayList<Node> {

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
                    flightService.addNewFlight(graph,values[0], values[1])
                }
            }
        } catch (e: Exception){
            println(e.stackTrace)
        }

        return graph
    }

}
