package blackjack.domain.state.running

import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_KING
import blackjack.domain.SPADE_NINE
import blackjack.domain.SPADE_QUEEN
import blackjack.domain.SPADE_TWO
import blackjack.domain.state.finished.Bust
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `카드를 뽑았을 때 21점을 초과하면 Bust`() {
        val state = Hit(SPADE_KING, SPADE_QUEEN)

        val expected = state.draw(SPADE_TWO)

        assertThat(expected).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드를 뽑았을 때 21점 이하면 Hit`() {
        val state = Hit(SPADE_ACE, SPADE_QUEEN)

        val expected = state.draw(SPADE_NINE)

        assertThat(expected).isInstanceOf(Hit::class.java)
    }
}
