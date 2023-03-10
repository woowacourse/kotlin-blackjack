package blackjack.domain.participants

import blackjack.domain.result.Outcome
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
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

    @ParameterizedTest
    @CsvSource(value = ["BLACKJACK, 25000", "WIN, 20000", "DRAW, 10000", "LOSE, 0"])
    fun `게임 OutCome에 따라 베팅금액에 대해 얻을 수 있는 금액 반환`(outcome: Outcome, money: Int) {
        val bettingMoney = Money(10000)
        assertThat(bettingMoney.getProfits(outcome)).isEqualTo(money)
    }
}
