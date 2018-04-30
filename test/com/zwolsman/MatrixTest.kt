package com.zwolsman

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.exp

internal class MatrixTest {

    @Test
    fun `Default generator inits with all 0's`() {
        val rows = 2
        val cols = 5
        val m = Matrix(rows, cols)

        for (x in 0 until rows) {
            for(y in 0 until cols) {
                assertEquals(0, m.data[x][y])
            }
        }
    }

    @Test
    fun `Multiply a matrix by a scalar`() {
        val matrix = Matrix(arrayOf(arrayOf(1,2), arrayOf(3,4)))
        val scalar = 1
        val result = matrix * scalar
        assertArrayEquals(matrix.data, result.data)
    }

    @Test
    fun `Multiply a matrix by a matrix element wise`() {
        val m1 = Matrix(arrayOf(arrayOf(1,2), arrayOf(3,4)))
        val m2 = Matrix(arrayOf(arrayOf(5,6), arrayOf(7,8)))
        val result = m1 * m2
        val target = Matrix(arrayOf(arrayOf(5, 12), arrayOf(21, 32)))
        assertArrayEquals(target.data, result.data)

    }

    @Test
    fun `Multiply a matrix by a matrix non matching elements throws`() {
        val m1 = Matrix(arrayOf(arrayOf(1), arrayOf(3)))
        val m2 = Matrix(arrayOf(arrayOf(5,6), arrayOf(7,8)))
        assertThrows(IllegalStateException::class.java) {
            val result = m1 * m2
        }
    }

    @Test
    fun `Add scalar to matrix`() {
        val matrix = Matrix(arrayOf(arrayOf(1,2), arrayOf(3,4)))
        val scalar = 0
        val result = matrix + scalar
        assertArrayEquals(matrix.data, result.data)
    }

    @Test
    fun `Add element wise`() {
        val m1 = Matrix(arrayOf(arrayOf(1,2), arrayOf(3,4)))
        val m2 = Matrix(arrayOf(arrayOf(5,6), arrayOf(7,8)))
        val result = m1 + m2
        val target = Matrix(arrayOf(arrayOf(6, 8), arrayOf(10, 12)))
        assertArrayEquals(target.data, result.data)
    }

    @Test
    fun `Transpose matrix`() {
        val m1 = Matrix(arrayOf(arrayOf(1,2,3), arrayOf(3,4,5)))
        val result = m1.transpose()
        val expected = Matrix(arrayOf(arrayOf(1,3), arrayOf(2,4), arrayOf(3,5)))

        assertArrayEquals(expected.data, result.data)
    }
}