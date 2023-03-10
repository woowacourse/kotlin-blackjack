package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_KING
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StayTest {
    @Test
    fun `끝낸 후에는 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { Stay(Cards()).draw(CLOVER_KING) }
    }

    @Test
    fun `스테이의 배당비율은 2배다`() {
        assertThat(Stay(Cards()).ratio).isEqualTo(2.0)
    }
}
