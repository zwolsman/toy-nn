package com.zwolsman

class Matrix(val rows: Int, val cols: Int, generator: (Int, Int) -> Int = Matrix.defaultGenerator) {
    operator fun times(scalar: Int): Matrix {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    operator fun times(m2: Matrix): Matrix {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

    constructor(arr: Array<Array<Int>>) : this(arr.size, arr.maxBy { it.size }?.max() ?: 0, { x, y ->
        arr[x][y]
    })


}