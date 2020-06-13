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
            // TODO refactor this code - boilerplate
            if (condition(it, destination, layovers)) {
                path.add(it)
                dfs(it, destination, layovers + 1)
                it.visited = false
                path.removeLast()
            } else {
                if (destination.name == it.name) {
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

        println("number of paths: ${paths.size}")

        return paths
    }

    @ExperimentalStdlibApi
    fun dfs(node: Node, destination: Node, layovers: Int) {
        node.visited = true
        node.edges.forEach {
            // TODO refactor this code - boilerplate
            if (condition(it, destination, layovers)) {
                path.add(it)
                dfs(it, destination, layovers + 1)
                it.visited = false
                path.removeLast()
            } else {
                if (destination.name == it.name) {
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
    }

    /**
     * Constraints:
     * - don't visit two times the same city during the trip
     * - no more than 2 layovers... level = 2
     * - find the destination
     * - node not included as part of the list of results
     */
    fun condition(node: Node, destination: Node, layovers: Int): Boolean {
        return !node.visited && layovers <= 1 && destination.name != node.name && !path.contains(node)
    }

}