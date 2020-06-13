package com.kaizen.airKaizen.model

class Node(
    var visited: Boolean = false,
    val name: String,
    var edges: ArrayList<Node> = arrayListOf()
)