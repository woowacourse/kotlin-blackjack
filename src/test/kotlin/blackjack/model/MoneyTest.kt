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

        money.plus(Money(500.0))

        val expected = 1_500.0

        assertThat(money.value).isEqualTo(expected)
    }

    @Test
    fun `돈을 뺀 금액 만큼 돈을 반환한다`() {
        val money = Money(1_000.0)

        money.minus(Money(500.0))

        val expected = 500.0

        assertThat(money.value).isEqualTo(expected)
    }

    @Test
    fun `매개변수로 받은 배수만큼 곱한 돈을 반환한다`() {
        val money = Money(1_000.0)

        money.multiple(1.5)

        val expected = 1_500.0

        assertThat(money.value).isEqualTo(expected)
    }

    @Test
    fun `수익금은 가지고 있던 현재의 값에서 초기값을 뺀 금액으로 반환한다`() {
        val initialMoney = Money(500.0)

        initialMoney.minus(Money(300.0))

        val expected = -300.0
        println(initialMoney.value)
        assertThat(initialMoney.getProfit()).isEqualTo(expected)
    }
}
