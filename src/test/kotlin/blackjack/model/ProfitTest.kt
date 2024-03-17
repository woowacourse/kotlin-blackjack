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
        val profit = Profit(input)

        assertThat(profit.amount).isEqualTo(input.toInt())
    }

    @ParameterizedTest
    @ValueSource(strings = ["한글", "abcd", "10000-5000"])
    fun `베팅금은 숫자로만 입력해야 한다`(notNumber: String) {
        assertThrows<IllegalArgumentException> { Profit(notNumber) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-2900", "0"])
    fun `베팅금은 0보다 큰 정수여야한다`(lessThanZero: String) {
        assertThrows<IllegalArgumentException> { Profit(lessThanZero) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["5000", "10000", "150000"])
    fun `무승부인 경우 수익을 0원으로 만든다`(bettingMoney: String) {
        val profit = Profit(bettingMoney)
        profit.giveBackBettingMoney()
        assertThat(profit.amount).isEqualTo(Profit.INITIAL_AMOUNT)
    }

    @ParameterizedTest
    @ValueSource(strings = ["5000", "10000", "150000"])
    fun `패배한 경우 베팅한 만큼의 금액을 모두 잃는다`(bettingMoney: String) {
        val profit = Profit(bettingMoney)
        profit.lostAllBettingMoney()
        assertThat(profit.amount).isEqualTo(-bettingMoney.toInt())
    }
}
