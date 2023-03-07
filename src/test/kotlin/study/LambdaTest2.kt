package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest2 {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    fun sumAllEven(numbers: List<Int>): Int {
        var total = 0
        for (number in numbers) {
            if (number % 2 == 0) {
                total += number
            }
        }
        return total
    }

    fun sumAllOverThree(numbers: List<Int>): Int {
        var total = 0
        for (number in numbers) {
            if (number > 3) {
                total += number
            }
        }
        return total
    }

    interface Calculate {
        fun condition(abc: Int): Boolean
    }

    fun sum(numbers: List<Int>, block: (Int) -> Boolean): Int {
        var total = 0
        for (number in numbers) {
            if (block(number)) {
                total += number
            }
        }
        return total
    }

    @Test
    fun `lamda`() {
        val actual = sum(numbers) { it % 2 == 0 }
        assertThat(actual).isEqualTo(sumAllEven(numbers))
    }
}
