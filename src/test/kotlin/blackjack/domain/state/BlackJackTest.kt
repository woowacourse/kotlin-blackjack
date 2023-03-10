package blackjack.domain.state

import blackjack.domain.state.Fixtures.CLOVER_KING
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackTest {
    @Test
    fun `블랙잭은 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { BlackJack(listOf()).draw(CLOVER_KING) }
    }
}
