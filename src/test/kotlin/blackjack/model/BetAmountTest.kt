package blackjack.model

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountTest {
    @ParameterizedTest
    @ValueSource(ints = [0, -1, -999])
    fun `배팅 금액이 0원 이하면 예외를 던진다`(amount: Int) {
        assertThrows<IllegalArgumentException> {
            BetAmount(amount)
        }
    }
}
