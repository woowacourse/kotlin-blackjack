package blackjack.domain.model.progress

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1, -1000])
    fun `0원 이하의 베팅 금액을 받을 수 없다`(rawBetAmount: Int) {
        assertThrows<IllegalArgumentException> { BetAmount(rawBetAmount) }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 1, 1000])
    fun `베팅 금액은 0원을 초과하는 값을 가진다`(rawBetAmount: Int) {
        assertThat(BetAmount(rawBetAmount).betAmount).isEqualTo(rawBetAmount)
    }
}
