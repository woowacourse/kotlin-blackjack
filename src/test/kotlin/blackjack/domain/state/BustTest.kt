package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_KING
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustTest {
    @Test
    fun `버스트 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { Bust(Cards()).draw(CLOVER_KING) }
    }
}
