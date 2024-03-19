package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ProfitTest {
    @ParameterizedTest
    @ValueSource(strings = ["2000", "10000", "30000"])
    fun `입력한 베팅금을 갖는다`(input: String) {
        val profit = Profit()
        profit.initBettingMoney(input)

        assertThat(profit.amount).isEqualTo(input.toDouble())
    }

    @ParameterizedTest
    @ValueSource(strings = ["한글", "abcd", "10000-5000"])
    fun `베팅금은 숫자로만 입력해야 한다`(notNumber: String) {
        val profit = Profit()
        assertThrows<IllegalArgumentException> { profit.initBettingMoney(notNumber) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-2900", "0"])
    fun `베팅금은 0보다 큰 정수여야한다`(lessThanZero: String) {
        val profit = Profit()
        assertThrows<IllegalArgumentException> { profit.initBettingMoney(lessThanZero) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["5000", "10000", "150000"])
    fun `무승부인 경우 수익을 0원으로 만든다`(bettingMoney: String) {
        val profit = Profit()
        profit.initBettingMoney(bettingMoney)
        profit.giveBackBettingMoney()
        assertThat(profit.amount).isEqualTo(Profit.INITIAL_AMOUNT)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5000", "10000", "150000"])
    fun `패배한 경우 베팅한 만큼의 금액을 모두 잃는다`(bettingMoney: String) {
        val profit = Profit()
        profit.initBettingMoney(bettingMoney)
        profit.lostAllBettingMoney()
        assertThat(profit.amount).isEqualTo(-bettingMoney.toDouble())
    }

    @ParameterizedTest
    @ValueSource(strings = ["5000", "10000", "150000"])
    fun `블랙잭으로 승리한 경우 베팅금의 150%를 얻는다`(bettingMoney: String) {
        val profit = Profit()
        profit.initBettingMoney(bettingMoney)
        profit.earnProfitForBlackJack()
        assertThat(profit.amount).isEqualTo(bettingMoney.toDouble() * Profit.BLACKJACK_ODDS)
    }
}
