package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MoneyTest {
    @Test
    fun `베팅 가능 최소 금액보다 작은 금액을 입력하면, 예외를 발생시킨다`() {
        assertThrows<IllegalArgumentException> {
            Money.of(0)
        }
    }

    @Test
    fun `Money 클래스끼리 더하였을 때, 올바른 금액을 가진 Money를 반환한다`() {
        val additionMoney = Money(1000)
        val actualResult = money + additionMoney
        assertThat(actualResult.amount).isEqualTo(3000)
    }

    @Test
    fun `Money에서 다른 Money를 차감했을 때, 올바른 금액을 가진 Money를 반환한다`() {
        val subtractionMoney = Money(2000)
        val actualResult = money - subtractionMoney
        assertThat(actualResult.amount).isEqualTo(0)
    }

    @Test
    fun `Money에 특정 소수를 곱하였을 때, 올바른 금액을 가진 Money를 반환한다`() {
        val actualResult = money * 1.5
        assertThat(actualResult.amount).isEqualTo(3000)
    }

    @ParameterizedTest
    @CsvSource(value = ["2000:-2000", "-2000:2000"], delimiter = ':')
    fun `Money의 값의 부호를 바꾸었을 때, 올바른 금액을 가진 Money를 반환한다`(
        input: Int,
        output: Int,
    ) {
        val money = Money(input)
        val actualResult = -money
        assertThat(actualResult.amount).isEqualTo(output)
    }

    companion object {
        private val money: Money = Money(2000)
    }
}
