package blackjack.domain.participants

import blackjack.domain.result.Outcome
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MoneyTest {

    @ParameterizedTest
    @ValueSource(ints = [1000, 1000000])
    fun `돈은 1000원으로 나누어떨어져야 한다`(money: Int) {
        assertDoesNotThrow {
            Money(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [999, 0, -1000])
    fun `돈이 1000원 이하인 경우 예외가 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            Money(money)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [12345, 120])
    fun `돈은 1000원으로 나누어떨어지지 않으면 예외가 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            Money(money)
        }
    }

    @Test
    fun `게임에서 블랙잭일 경우 10000원을 베팅하면 25000원의 금액 반환`() {
        val bettingMoney = Money(10000)
        assertThat(bettingMoney.getProfits(Outcome.BLACKJACK)).isEqualTo(25000)
    }

    @Test
    fun `게임에서 이겼을 경우 10000원을 베팅하면 20000원의 금액 반환`() {
        val bettingMoney = Money(10000)
        assertThat(bettingMoney.getProfits(Outcome.WIN)).isEqualTo(20000)
    }

    @Test
    fun `게임에서 비겼을 경우 10000원을 베팅하면 10000원의 금액 반환`() {
        val bettingMoney = Money(10000)
        assertThat(bettingMoney.getProfits(Outcome.DRAW)).isEqualTo(10000)
    }

    @Test
    fun `게임에서 졌을 경우 10000원을 베팅하면 0원의 금액 반환`() {
        val bettingMoney = Money(10000)
        assertThat(bettingMoney.getProfits(Outcome.LOSE)).isEqualTo(0)
    }
}
