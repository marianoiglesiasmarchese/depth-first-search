package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.springframework.stereotype.Service

@Service
class RouteService {

    @ExperimentalStdlibApi
    fun findPaths(origin: Node, destination: Node): List<List<Node>> {

        var layovers = 0
        val path = arrayListOf<Node>()
        val paths = arrayListOf<List<Node>>()

        path.add(origin)
        origin.edges.forEach {
            evaluate(it, destination, layovers, path, paths)
        }

        return paths
    }

    @ExperimentalStdlibApi
    private fun dfs(
        node: Node,
        destination: Node,
        layovers: Int,
        path: ArrayList<Node>,
        paths: ArrayList<List<Node>>
    ) {
        node.visited = true
        node.edges.forEach {
            evaluate(it, destination, layovers, path, paths)
        }
    }

    @ExperimentalStdlibApi
    private fun evaluate(
        current: Node,
        destination: Node,
        layovers: Int,
        path: ArrayList<Node>,
        paths: ArrayList<List<Node>>
    ){
        if (condition(current, destination, layovers, path)) {
            path.add(current)
            dfs(current, destination, layovers + 1, path, paths)
            current.visited = false
            path.removeLast()
        } else {
            if (destination.name == current.name) {
                // save the path
                val currentPath = path.toMutableList()
                currentPath.add(destination)
                paths.add(currentPath)
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
    private fun condition(
        node: Node,
        destination: Node,
        layovers: Int,
        path: ArrayList<Node>
    ): Boolean {
        return !node.visited && layovers <= 1 && destination.name != node.name && !path.contains(node)
    }

}