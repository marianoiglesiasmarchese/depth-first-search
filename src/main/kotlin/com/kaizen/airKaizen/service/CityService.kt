package com.kaizen.airKaizen.service

import com.kaizen.airKaizen.model.Node
import org.springframework.stereotype.Service

@Service
class CityService {

    fun cities(graph: ArrayList<Node>) = graph.map { it.name }

}