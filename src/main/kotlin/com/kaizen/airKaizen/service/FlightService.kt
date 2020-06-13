package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.springframework.stereotype.Service

@Service
class FlightService {

    fun addNewFlight(graph: ArrayList<Node>, origin: String, destination: String) {
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