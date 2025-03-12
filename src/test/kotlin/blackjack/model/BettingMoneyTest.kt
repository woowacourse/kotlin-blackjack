package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 300_000_001])
    fun `베팅 금액이 1원 이상 최대 3억원이 아닐 경우 예외를 발생시킨다`(money: Int) {
        assertThrows<IllegalArgumentException> { BettingMoney(money) }
    }

    @Test
    fun `베팅 금액은 베팅한 금액을 가진다`() {
        val bettingMoney = BettingMoney(100_000)

        val actual = bettingMoney.money

        val expected = 100_000

        assertThat(actual).isEqualTo(expected)
    }
}
