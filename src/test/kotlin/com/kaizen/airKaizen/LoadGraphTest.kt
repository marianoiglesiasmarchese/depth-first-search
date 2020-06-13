package com.kaizen.airKaizen

import com.kaizen.airKaizen.model.Node
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LoadGraphTest(
    @Qualifier("graph") val graph: ArrayList<Node>
) {

    @Test
    fun `should have 5 cities`() {
        // Arrange & Act, not necessary

        // Assert
        Assertions.assertEquals(5, graph.size)
    }

    @Test
    fun `should have 4 relationships per city`() {
        // Arrange & Act, not necessary

        // Assert
        graph.forEach {
            Assertions.assertEquals(4, it.edges.size)
        }
    }

}