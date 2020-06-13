package com.kaizen.airKaizen

import com.kaizen.airKaizen.model.Node
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class Load(){

    /**
     * Constraint
     *
     * //All flights are bi-directional; AirKaizen serves to and from all cities
    //● AirKaizen uses the same identifier for a flight between two cities. So Chicago → Grand
    //Rapids and Grand Rapids → Chicago both share flight id KZ01.
     */
    @Bean(name = ["graph"])
    fun loadData(): List<Node> {

        // build node
        var node1 = Node(name = "1")
        var node2 = Node(name = "2")
        var node3 = Node(name = "3")
        var node4 = Node(name = "4")
        var node5 = Node(name = "5")

        // load edges
        node1.edges.addAll(listOf(node2, node3, node4, node5))
        node2.edges.addAll(listOf(node1, node3, node4, node5))
        node3.edges.addAll(listOf(node2, node1, node4, node5))
        node4.edges.addAll(listOf(node2, node3, node1, node5))
        node5.edges.addAll(listOf(node2, node3, node4, node1))

        // build vertices
        return listOf(node1, node2, node3, node4, node5)
    }

}
