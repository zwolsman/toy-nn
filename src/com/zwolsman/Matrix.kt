package com.zwolsman

class Matrix(val rows: Int, val cols: Int, generator: (Int, Int) -> Int = Matrix.defaultGenerator) {
    companion object {
        val defaultGenerator = { _: Int, _: Int ->
            0
        }
    }

    var data = Array(rows) { x ->
        Array(cols) { y ->
            generator(x, y)
        }
    }

}