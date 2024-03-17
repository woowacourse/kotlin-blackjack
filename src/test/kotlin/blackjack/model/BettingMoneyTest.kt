package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(strings = ["2000", "10000", "30000"])
    fun `입력한 베팅금을 갖는다`(input: String) {
        val bettingMoney = BettingMoney(input)

        assertThat(bettingMoney.amount).isEqualTo(input.toInt())
    }

    @ParameterizedTest
    @ValueSource(strings = ["한글", "abcd", "10000-5000"])
    fun `베팅금은 숫자로만 입력해야 한다`(input: String) {
        assertThrows<IllegalArgumentException> { BettingMoney(input) }
    }

    @ParameterizedTest
    @ValueSource(strings = ["-2900", "0"])
    fun `베팅금은 0보다 큰 정수여야한다`(lessThanZero: String) {
        assertThrows<IllegalArgumentException> { BettingMoney(lessThanZero) }
    }
}
