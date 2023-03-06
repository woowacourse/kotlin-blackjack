package blackjack.model

import model.Bet
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BetTest {
    @Test
    fun `배팅 금액이 100 미만이면 에러를 발생한다`() {
        assertThrows<IllegalArgumentException> { Bet(99) }
    }

    @Test
    fun `배팅 금액이 1000000 초과면 에러를 발생한다`() {
        assertThrows<IllegalArgumentException> { Bet(1000001) }
    }
}
