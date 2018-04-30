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
                assertEquals(0, m.data[x][y])
            }
        }
    }
}