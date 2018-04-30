package com.zwolsman

import org.junit.jupiter.api.Test

internal class NeuralNetworkTest {
    @Test
    fun `Creating nn with random values`() {
        val nn = NeuralNetwork(2, 2, 1)
        print(nn)
    }

    @Test
    fun `Creating nn based off other nn`() {
        val nn = NeuralNetwork(2,2,1)
        val nn2 = NeuralNetwork(nn)
    }
}