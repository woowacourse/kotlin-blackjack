package blackjack

import blackjack.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `블랙잭은 배팅금액의 150% 금액을 얻는다`() {
        val money = Money.from(1_000L)
        val expected = Money.from(1_500L)

        val actual = Money.toBlackjackMoney(money)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `승리시 배팅금액의 100% 금액을 얻는다`() {
        val money = Money.from(1_000L)
        val expected = Money.from(1_000L)

        val actual = Money.toWinMoney(money)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `비길 경우 배팅금액의 0% 금액을 얻는다`() {
        val expected = Money.from(0L)

        val actual = Money.toDrawMoney()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Bust 또는 질 경우 배팅금액의 100% 금액을 잃는다`() {
        val money = Money.from(1_000L)
        val expected = Money.from(-1_000L)

        val actual = Money.toLoseMoney(money)

        assertThat(actual).isEqualTo(expected)
    }
}
