package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `profit비율에 맞춰 금액이 서로 바뀐다`() {
        val dealerMoney = Money(1000)
        val playerMoney = Money(1000)

        dealerMoney.applyProfitRate(playerMoney, 0.0)

        val dealerExpected = Money(1000)
        val dealerActual = dealerMoney.copy()

        val playerExpected = Money(0)
        val playerActual = playerMoney.copy()

        assertThat(dealerActual).isEqualTo(dealerExpected)
        assertThat(playerActual).isEqualTo(playerExpected)
    }
}
