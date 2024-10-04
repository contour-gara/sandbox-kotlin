package org.contourgara.arrow

import arrow.core.Either
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

class DivideTest : WordSpec({
    "割り算" When {
        "0 で割る場合" should {
            "左で例外が返る" {
                val actual = Divide().divide(1, 0)
                val expected = Either.Left("除数を 0 にしてはいけません。")
                actual shouldBe expected
            }
        }

        "6 割る 2" should {
            "右で 3 が返る" {
                val actual = Divide().divide(6, 2)
                val expected = Either.Right(3)
                actual shouldBe expected
            }
        }
    }
})
