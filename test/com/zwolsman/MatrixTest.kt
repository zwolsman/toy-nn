package com.zwolsman

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MatrixTest {

    @Test
    fun `Default generator inits with all 0's`() {
        val rows = 2
        val cols = 5
        val m = Matrix(rows, cols)

        for (x in 0 until rows) {
            for(y in 0 until cols) {
                assertEquals(0.0, m.data[x][y])
            }
        }
    }

    @Test
    fun `Multiply a matrix by a scalar`() {
        val matrix = Matrix(arrayOf(arrayOf(1.0,2.0), arrayOf(3.0,4.0)))
        val scalar = 1.0
        val result = matrix * scalar
        assertArrayEquals(matrix.data, result.data)
    }

    @Test
    fun `Multiply a matrix by a matrix element wise`() {
        val m1 = Matrix(arrayOf(arrayOf(1.0,2.0), arrayOf(3.0,4.0)))
        val m2 = Matrix(arrayOf(arrayOf(5.0,6.0), arrayOf(7.0,8.0)))
        val result = m1 * m2
        val target = Matrix(arrayOf(arrayOf(5.0, 12.0), arrayOf(21.0, 32.0)))
        assertArrayEquals(target.data, result.data)

    }

    @Test
    fun `Multiply a matrix by a matrix non matching elements throws`() {
        val m1 = Matrix(arrayOf(arrayOf(1.0), arrayOf(3.0)))
        val m2 = Matrix(arrayOf(arrayOf(5.0,6.0), arrayOf(7.0,8.0)))
        assertThrows(IllegalStateException::class.java) {
            val result = m1 * m2
        }
    }

    @Test
    fun `Add scalar to matrix`() {
        val matrix = Matrix(arrayOf(arrayOf(1.0,2.0), arrayOf(3.0,4.0)))
        val scalar = 0.0
        val result = matrix + scalar
        assertArrayEquals(matrix.data, result.data)
    }

    @Test
    fun `Add element wise`() {
        val m1 = Matrix(arrayOf(arrayOf(1.0,2.0), arrayOf(3.0,4.0)))
        val m2 = Matrix(arrayOf(arrayOf(5.0,6.0), arrayOf(7.0,8.0)))
        val result = m1 + m2
        val target = Matrix(arrayOf(arrayOf(6.0, 8.0), arrayOf(10.0, 12.0)))
        assertArrayEquals(target.data, result.data)
    }

    @Test
    fun `Transpose matrix`() {
        val m1 = Matrix(arrayOf(arrayOf(1.0,2.0,3.0), arrayOf(3.0,4.0,5.0)))
        val result = m1.transpose()
        val expected = Matrix(arrayOf(arrayOf(1.0,3.0), arrayOf(2.0,4.0), arrayOf(3.0,5.0)))

        assertArrayEquals(expected.data, result.data)
    }
}