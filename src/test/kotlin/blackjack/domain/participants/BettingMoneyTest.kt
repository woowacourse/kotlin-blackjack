package blackjack.domain.participants

import blackjack.domain.result.Outcome
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class BettingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = [1000, 1000000])
    fun `돈은 1000원으로 나누어떨어져야 한다`(money: Int) {
        assertDoesNotThrow {
            BettingMoney(money)
        }
    }

    @Test
    fun `돈이 음수인 경우 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BettingMoney(-1000)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [12345, 120])
    fun `돈은 1000원으로 나누어떨어지지 않으면 예외가 발생한다`(money: Int) {
        assertThrows<IllegalArgumentException> {
            BettingMoney(money)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["BLACKJACK, 15000", "WIN, 10000", "DRAW, 0", "LOSE, -10000"])
    fun `게임 OutCome에 따라 베팅금액에 대한 최종 수밈 반환`(outcome: Outcome, money: Int) {
        val bettingMoney = BettingMoney(10000)
        assertThat(bettingMoney.getProfits(outcome)).isEqualTo(money)
    }
}
