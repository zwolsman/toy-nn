package com.zwolsman

import org.junit.jupiter.api.Test
import java.util.*

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

    @Test
    fun `Iets`() {

        val nn = NeuralNetwork(2, 2, 1)

        class TrainingData(val training:Matrix, val label:Matrix)

        val trainingData = listOf(
                TrainingData(Matrix(arrayOf(arrayOf(1.0,0.0))), Matrix(arrayOf(arrayOf(0.0)))),
                TrainingData(Matrix(arrayOf(arrayOf(1.0,1.0))), Matrix(arrayOf(arrayOf(1.0)))),
                TrainingData(Matrix(arrayOf(arrayOf(0.0,1.0))), Matrix(arrayOf(arrayOf(0.0)))),
                TrainingData(Matrix(arrayOf(arrayOf(0.0,0.0))), Matrix(arrayOf(arrayOf(0.0))))
        )

        for(abc in 0 until 100) {
            val trainingData = trainingData.get(Random().nextInt(trainingData.size))
            nn.train(trainingData.training, trainingData.label)

            print(nn.predict(Matrix(arrayOf(arrayOf(1.0,1.0)))))
        }
    }
}