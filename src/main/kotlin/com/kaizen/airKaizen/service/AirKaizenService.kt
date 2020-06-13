package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.springframework.stereotype.Service

@Service
class AirKaizenService {

    val path = arrayListOf<Node>()
    val paths = arrayListOf<List<Node>>()

    @ExperimentalStdlibApi
    fun findPaths(origin: Node, destination: Node): List<List<Node>> {

        var layovers = 0

        path.add(origin)
        origin.edges.forEach {
            evaluate(it, destination, layovers)
        }

        println("number of paths: ${paths.size}")

        return paths
    }

    @ExperimentalStdlibApi
    private fun dfs(node: Node, destination: Node, layovers: Int) {
        node.visited = true
        node.edges.forEach {
            evaluate(it, destination, layovers)
        }
    }

    @ExperimentalStdlibApi
    private fun evaluate(current: Node, destination: Node, layovers: Int){
        if (condition(current, destination, layovers)) {
            path.add(current)
            dfs(current, destination, layovers + 1)
            current.visited = false
            path.removeLast()
        } else {
            if (destination.name == current.name) {
                // save the path
                paths.add(path.toList())
                // print path
                print("path: ")
                for (node in path) {
                    print("${node.name}  ," )
                }
                print("${destination.name}" )
                println()
            }
        }
    }

    /**
     * Constraints:
     * - don't visit two times the same city during the trip
     * - no more than 2 layovers... level = 2
     * - find the destination
     * - node not included as part of the list of results
     */
    private fun condition(node: Node, destination: Node, layovers: Int): Boolean {
        return !node.visited && layovers <= 1 && destination.name != node.name && !path.contains(node)
    }

}