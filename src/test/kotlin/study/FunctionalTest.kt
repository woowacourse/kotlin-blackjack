package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FunctionalTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    private fun sumAll(numbers: List<Int>) = numbers.sum()

    private fun sumAllEven(numbers: List<Int>): Int = numbers.filter { it % 2 == 0 }.sum()

    private fun sumAllOverThree(numbers: List<Int>): Int = numbers.filter { it > 3 }.sum()

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
