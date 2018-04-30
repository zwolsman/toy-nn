package com.zwolsman

class NeuralNetwork(val inputNodes: Int, val hiddenNodes: Int, val outputNodes: Int) {
    class ActivationFunction(val x: (Double) -> Double, val y: (Double) -> Double)

    companion object {
        val NoMutation = { value: Double ->
            value
        }

        val sigmond = ActivationFunction(
                { x -> 1 / (1 + Math.exp(-x)) },
                { y -> y * (1 - y) })
        val tanh = ActivationFunction(
                { x -> Math.tanh(x) },
                { y -> 1 - (y * y) }
        )
    }


    var weightsIh: Matrix
    var weightsHo: Matrix

    var biasH: Matrix
    var biasO: Matrix

    var learningRate = 0.1
    var activiationFunction = sigmond

    init {
        weightsIh = Matrix(hiddenNodes, inputNodes, Matrix.randomGenerator)
        weightsHo = Matrix(outputNodes, hiddenNodes, Matrix.randomGenerator)
        biasH = Matrix(hiddenNodes, 1, Matrix.randomGenerator)
        biasO = Matrix(outputNodes, 1, Matrix.randomGenerator)

    }

    constructor(weightsIh: Matrix, weightsHo: Matrix, biasH: Matrix, biasO: Matrix) : this(weightsIh.cols, weightsHo.cols, weightsHo.rows) {
        this.weightsIh = weightsIh.copy()
        this.weightsHo = weightsHo.copy()
        this.biasH = biasH.copy()
        this.biasO = biasO.copy()
    }

    constructor(parent: NeuralNetwork, mutateFunction: (Double) -> Double = NeuralNetwork.NoMutation) : this(parent.weightsIh, parent.weightsHo, parent.biasH, parent.biasO) {
        weightsIh.map { _, _, value -> mutateFunction(value) }
        weightsHo.map { _, _, value -> mutateFunction(value) }
        biasH.map { _, _, value -> mutateFunction(value) }
        biasO.map { _, _, value -> mutateFunction(value) }
    }
}