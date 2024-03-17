package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(strings = ["2000", "10000", "30000"])
    fun `입력한 베팅금을 갖는다`(input: String) {
        val bettingMoney = BettingMoney(input)

        assertThat(bettingMoney.amount).isEqualTo(input.toInt())
    }
}
