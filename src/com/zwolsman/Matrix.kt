package com.zwolsman

import java.util.*
import kotlin.math.roundToInt

class Matrix(val rows: Int, val cols: Int, generator: (Int, Int) -> Double = Matrix.defaultGenerator) {


    constructor(arr: Array<Array<Double>>) : this(arr.size, arr.maxBy { it.size }?.size ?: 0, { x, y ->
        arr[x][y]
    })

    companion object {
        val defaultGenerator = { _: Int, _: Int ->
            0.0
        }
        val randomGenerator = { _: Int, _: Int ->
            Random().nextDouble() * 2 - 1
        }
    }

    var data = Array(rows) { x ->
        Array(cols) { y ->
            generator(x, y)
        }
    }

    operator fun plus(scalar: Double) = scalarOperation(scalar, Double::plus)
    operator fun minus(scalar: Double) = scalarOperation(scalar, Double::minus)
    operator fun times(scalar: Double) = scalarOperation(scalar, Double::times)
    operator fun div(scalar: Double) = scalarOperation(scalar, Double::div)

    operator fun plus(other: Matrix) = elementWiseOperation(other, Double::plus)
    operator fun minus(other: Matrix) = elementWiseOperation(other, Double::minus)
    operator fun times(other: Matrix) = elementWiseOperation(other, Double::times)
    operator fun div(other: Matrix) = elementWiseOperation(other, Double::div)

    private fun scalarOperation(scalar: Double, operator: (Double, Double) -> Double): Matrix {
        val result = this.copy()
        result.map { _, _, value ->
            operator(value, scalar)
        }
        return result
    }

    private fun elementWiseOperation(other: Matrix, operator: (Double, Double) -> Double): Matrix {
        check(other.cols == this.cols)
        check(other.rows == this.rows)

        val result = this.copy()
        result.map { x, y, value ->
            operator(value, other.data[x][y])
        }
        return result
    }

    fun transpose() = Matrix(cols, rows) { x, y ->
        data[y][x]
    }

    fun copy() = Matrix(data)

    fun map(mapper: (Int, Int, Double) -> Double) {
        data = data.mapIndexed { x, row ->
            row.mapIndexed { y, col ->
                mapper(x, y, col)
            }.toTypedArray()
        }.toTypedArray()
    }


    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendln("[MATRIX]")

        for (row in 0 until rows) {
            sb.append("  ")
            sb.appendln(data[row].joinToString())
        }

        return sb.toString()
    }
}