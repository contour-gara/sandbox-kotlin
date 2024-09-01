package listmeenperformance

/**
 * 最大値と最小値を除いた平均を算出します。
 * 最大値と最小値が複数ある場合は、全て無視します。
 */
class MeanWithoutMxMin {
    companion object {
        const val MINIMUM_LIST_SIZE = 3
    }

    /**
     * ループを用いず、標準 API で可読性が高いように実装しています。
     */
    fun executeWithoutLoop(input: List<Int>): Double {
        require(input.size >= MINIMUM_LIST_SIZE) { "要素が 3 以上の配列を入力してください。" }

        val max = input.max()
        val min = input.min()

        return input.filter { e -> e != min && e != max }.average()
    }

    /**
     * ループ 1 回だけ用いることで、パフォーマンスが出るように実装しています。
     * コメントアウトしている実装に差し替えるとなおパフォーマンスがいいですが、可読性のために data class を使用しています。
     */
    fun executeWithLoop(input: List<Int>): Double {
        require(input.size >= MINIMUM_LIST_SIZE) { "要素が 3 以上の配列を入力してください。" }

        var sum = 0L
        var count = 0

        var maxParam = MaxParam(Int.MIN_VALUE, 0)
//        var max = Int.MIN_VALUE
//        var min = Int.MAX_VALUE

        var minParam = MinParam(Int.MAX_VALUE, 0)
//        var maxCount = 0
//        var minCount = 0

        for (num in input) {
            sum += num
            count++

            maxParam = maxParam.update(num)
//            if (num > max) {
//                max = num
//                maxCount = 1
//            } else if (num == max) {
//                maxCount++
//            }

            minParam = minParam.update(num)
//            if (num < min) {
//                min = num
//                minCount = 1
//            } else if (num == min) {
//                minCount++
//            }
        }

        sum -= maxParam.max * maxParam.maxCount
        sum -= minParam.min * minParam.minCount
        count -= (maxParam.maxCount + minParam.minCount)
//        sum -= max * maxCount
//        sum -= min * minCount
//        count -= (maxCount + minCount)

        return sum.toDouble() / count
    }

    private data class MaxParam(val max: Int, val maxCount: Int) {
        fun update(input: Int): MaxParam {
            return when {
                input > max -> MaxParam(input, 1)
                input == max -> MaxParam(max, maxCount + 1)
                else -> this
            }
        }
    }

    private data class MinParam(val min: Int, val minCount: Int) {
        fun update(input: Int): MinParam {
            return when {
                input < min -> MinParam(input, 1)
                input == min -> MinParam(min, minCount + 1)
                else -> this
            }
        }
    }
}
