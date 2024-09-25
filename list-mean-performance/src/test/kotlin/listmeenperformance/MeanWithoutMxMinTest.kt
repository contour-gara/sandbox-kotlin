package listmeenperformance

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.WordSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MeanWithoutMxMinTest : WordSpec() {
    lateinit var sut: MeanWithoutMxMin

    data class Provider(val input: List<Int>, val expected: Double)

    init {
        beforeTest {
            sut = MeanWithoutMxMin()
        }

        "ループを用いないメッソッド" should {
            withData(
                mapOf(
                    "最大値と最小値を除いた平均を算出できる1" to Provider(listOf(1, 2, 5), 2.0),
                    "最大値と最小値を除いた平均を算出できる2" to Provider(listOf(1, 1, 2, 3, 4, 5, 5), 3.0)
                )
            ) { (input, expected) ->
                sut.executeWithoutLoop(input) shouldBe expected
            }

            "最大値と最小値が同じ場合は例外が返る" {
                shouldThrowExactly<IllegalArgumentException> {
                    sut.executeWithoutLoop(listOf(1, 1, 1, 1))
                }.message shouldBe "最大値と最小値が同じです。"
            }

            "長いリストの場合の時間計測" {
                val start = System.currentTimeMillis()
                println(sut.executeWithoutLoop(List(10000000) { it }))
                val end = System.currentTimeMillis()
                println("処理時間: ${end - start} ms")
            }
        }

        "ループを用いるメッソッド" should {
            withData(
                mapOf(
                    "最大値と最小値を除いた平均を算出できる1" to Provider(listOf(1, 2, 5), 2.0),
                    "最大値と最小値を除いた平均を算出できる2" to Provider(listOf(1, 1, 2, 3, 4, 5, 5), 3.0)
                )
            ) { (input, expected) ->
                sut.executeWithLoop(input) shouldBe expected
            }

            "最大値と最小値が同じ場合は例外が返る" {
                shouldThrowExactly<IllegalArgumentException> {
                    println(sut.executeWithLoop(listOf(1, 1, 1, 1)))
                }.message shouldBe "最大値と最小値が同じです。"
            }

            "長いリストの場合の時間計測" {
                val start = System.currentTimeMillis()
                println(sut.executeWithLoop(List(10000000) { it }))
                val end = System.currentTimeMillis()
                println("処理時間: ${end - start} ms")
            }
        }
    }
}
