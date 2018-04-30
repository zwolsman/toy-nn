package com.zwolsman

class Matrix(val rows: Int, val cols: Int, generator: (Int, Int) -> Int = Matrix.defaultGenerator) {


    constructor(arr: Array<Array<Int>>) : this(arr.size, arr.maxBy { it.size }?.size ?: 0, { x, y ->
        arr[x][y]
    })

    companion object {
        val defaultGenerator = { _: Int, _: Int ->
            0
        }
    }

    var data = Array(rows ) { x ->
        Array(cols) { y ->
            generator(x, y)
        }
    }

    operator fun times(scalar: Int): Matrix {
        val result = this.copy()
        result.map {_,_, value ->
            value * scalar
        }
        return result
    }

    operator fun times(m2: Matrix): Matrix {
        check(m2.cols == this.cols)
        check(m2.rows == this.rows)

        val result = this.copy()
        result.map {x,y, value ->
            value * m2.data[x][y]
        }
        return result
    }

    fun copy() = Matrix(data)

    fun map(mapper:(Int, Int, Int) -> Int) {
        data = data.mapIndexed {x, row ->
            row.mapIndexed { y, col ->
                mapper(x,y, col)
            }.toTypedArray()
        }.toTypedArray()
    }

}