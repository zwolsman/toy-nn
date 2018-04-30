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

    operator fun plus(scalar: Int) = scalarOperation(scalar, Math::addExact)
    operator fun minus(scalar: Int) = scalarOperation(scalar, Math::subtractExact)
    operator fun times(scalar: Int) = scalarOperation(scalar, Math::multiplyExact)
    operator fun div(scalar: Int) = scalarOperation(scalar, Math::floorDiv)

    operator fun plus(other: Matrix) = elementWiseOperation(other, Math::addExact)
    operator fun minus(other: Matrix) = elementWiseOperation(other, Math::subtractExact)
    operator fun times(other: Matrix) = elementWiseOperation(other, Math::multiplyExact)
    operator fun div(other: Matrix) = elementWiseOperation(other, Math::floorDiv)

    private fun scalarOperation(scalar:Int, operator: (Int, Int) -> Int) : Matrix {
        val result = this.copy()
        result.map {_,_, value ->
            operator(value, scalar)
        }
        return result
    }

    private fun elementWiseOperation(other: Matrix, operator: (Int, Int) -> Int) : Matrix {
        check(other.cols == this.cols)
        check(other.rows == this.rows)

        val result = this.copy()
        result.map {x,y, value ->
            operator(value,other.data[x][y])
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