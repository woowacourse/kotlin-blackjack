package blackjack.domain.model.participant

import blackjack.domain.model.betting.BetAmount
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountTest {
    @ParameterizedTest
    @ValueSource(
        doubles = [0.0, 1000.0, 50000.0],
    )
    fun `베팅 금액은 0원 이상이어야 한다`(money: Double) {
        assertDoesNotThrow {
            BetAmount(money)
        }
    }

    @Test
    fun `베팅 금액은 0원 미만이면 안 된다`() {
        assertThrows<IllegalArgumentException> {
            BetAmount(-1000.0)
        }
    }
}
