package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `베팅 금액은 베팅한 금액을 가진다`() {
        val money = Money(100_000.0)

        val actual = money.value

        val expected = 100_000.0

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈을 더한 금액 만큼 돈을 반환한다`() {
        val money = Money(1_000.0)

        val actual: Double = money.plus(Money(500.0)).value

        val expected = 1_500.0

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `돈을 뺀 금액 만큼 돈을 반환한다`() {
        val money = Money(1_000.0)

        val actual: Double = money.minus(Money(500.0)).value

        val expected = 500.0

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `매개변수로 받은 배수만큼 곱한 돈을 반환한다`() {
        val money = Money(1_000.0)

        val actual: Double = money.multiple(1.5).value

        val expected = 1_500.0

        assertThat(actual).isEqualTo(expected)
    }
}
