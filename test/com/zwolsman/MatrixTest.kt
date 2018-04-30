package com.zwolsman

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.UnsupportedOperationException

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
        assertEquals(matrix.data, result.data)
    }

    @Test
    fun `Multiply a matrix by a matrix element wise`() {
        val m1 = Matrix(arrayOf(arrayOf(1,2), arrayOf(3,4)))
        val m2 = Matrix(arrayOf(arrayOf(5,6), arrayOf(7,8)))
        val result = m1 * m2
        val target = Matrix(arrayOf(arrayOf(6, 12), arrayOf(21, 32)))
        assertEquals(target.data, result.data)

    }

    @Test
    fun `Multiply a matrix by a matrix non matching elements throws`() {
        val m1 = Matrix(arrayOf(arrayOf(1), arrayOf(3)))
        val m2 = Matrix(arrayOf(arrayOf(5,6), arrayOf(7,8)))
        assertThrows(UnsupportedOperationException::class.java) {
            val result = m1 * m2
        }
    }
}