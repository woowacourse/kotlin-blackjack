package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FunctionalTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sumAll(numbers: List<Int>): Int {
        return sumWithCondition(numbers) {
            true
        }
    }
    @Test
    fun sumAllEven(numbers: List<Int>): Int {
        return sumWithCondition(numbers) {
            it % 2 == 0
        }
    }
    @Test
    fun sumAllOverThree(numbers: List<Int>): Int {
        return sumWithCondition(numbers) {
            it > 3
        }
    }

    private fun sumWithCondition(numbers: List<Int>, condition: SumCondition): Int {
        var total = 0
        for (number in numbers) {
            if (condition.condition(number))
                total += number
        }
        return total
    }

    fun interface SumCondition {
        fun condition(number: Int): Boolean
    }

    @Test
    fun `1부터 6까지 합산 테스트`() {
        assertThat(sumAll(numbers)).isEqualTo(21)
    }

    @Test
    fun `1부터 6까지 짝수의 합산 테스트`() {
        assertThat(sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    fun `1부터 6까지 3보타 큰 숫자들 테스트`() {
        assertThat(sumAllOverThree(numbers)).isEqualTo(15)
    }
}
