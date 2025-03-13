package blackjack.domain.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BetTest {
    @Test
    fun `베팅 금액이 0보다 작을 시 오류가 발생한다`() {
        assertThrows<IllegalArgumentException> { Bet(-1) }
    }
}
