package org.contourgara.arrow

import arrow.core.Either
import arrow.core.left
import arrow.core.right

class Divide {
    fun divide(a: Int, b: Int): Either<String, Int> {
        return when (b == 0) {
            true -> "除数を 0 にしてはいけません。".left()
            false -> (a / b).right()
        }
    }
}
