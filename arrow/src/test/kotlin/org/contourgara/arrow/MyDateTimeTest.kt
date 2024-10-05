package org.contourgara.arrow

import arrow.core.Either
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class MyDateTimeTest : WordSpec({
    "インスタンス生成" When {
        "時間が数字でない場合" should {
            "左で例外が返る" {
                val actual = MyDateTime.of("数字", 123)
                val expected = Either.Left(MyDateTimeError.DateError("日付が不正です。"))
                actual shouldBe expected
            }
        }

        "時間が範囲外の場合" should {
            "左で例外が返る" {
                val actual = MyDateTime.of("20241005", 2400)
                val expected = Either.Left(MyDateTimeError.TimeError("時間が不正です。"))
                actual shouldBe expected
            }
        }

        "分が範囲外の場合" should {
            "左で例外が返る" {
                val actual = MyDateTime.of("20241005", 61)
                val expected = Either.Left(MyDateTimeError.TimeError("時間が不正です。"))
                actual shouldBe expected
            }
        }

        "日付が 20241005 で時間が 100 の場合" should {
            "LocalDateTime 型で取得できる" {
                val actual = MyDateTime.of("20241005", 100).getOrNull()!!.dateTime
                val expected = LocalDateTime.of(2024, 10, 5, 1, 0, 0)
                actual shouldBe expected
            }
        }
    }
})
