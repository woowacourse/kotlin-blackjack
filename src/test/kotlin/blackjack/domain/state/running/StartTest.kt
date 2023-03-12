package blackjack.domain.state.running

import blackjack.domain.SPADE_ACE
import blackjack.domain.SPADE_FOUR
import blackjack.domain.SPADE_JACK
import blackjack.domain.SPADE_SIX
import blackjack.domain.state.finished.Blackjack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StartTest {
    @Test
    fun `처음 카드 1장을 뽑으면 Start`() {
        val state = Start()

        val expected = state.draw(SPADE_ACE)

        assertThat(expected).isInstanceOf(Start::class.java)
    }

    @Test
    fun `카드가 1장 있는 상태로 1장 더 뽑아서 21점이면 Blackjack`() {
        val state = Start(SPADE_ACE)

        val expected = state.draw(SPADE_JACK)

        assertThat(expected).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `카드가 1장 있는 상태로 1장 더 뽑아서 21점 미만이면 Hit`() {
        val state = Start(SPADE_FOUR)

        val expected = state.draw(SPADE_SIX)

        assertThat(expected).isInstanceOf(Hit::class.java)
    }
}
