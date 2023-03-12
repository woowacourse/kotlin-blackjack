package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_FOUR
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_NINE
import blackjack.domain.state.Fixtures.CLOVER_THREE
import blackjack.domain.state.Fixtures.CLOVER_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class HitTest {
    @Test
    fun `히트는 카드를 뽑을 수 있다`() {
        val state: State = Hit(Cards(CLOVER_KING, CLOVER_THREE))
        assertDoesNotThrow { state.draw(CLOVER_FOUR) }
    }

    @Test
    fun `히트에서 히트로 갈 수 있다`() {
        // given
        var state: State = FirstTurn().draw(CLOVER_KING).draw(CLOVER_THREE)

        // when
        state = state.draw(CLOVER_FOUR)

        // then
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `히트에서 블랙잭으로 갈 수 있다`() {
        // given
        var state: State = FirstTurn().draw(CLOVER_KING).draw(CLOVER_NINE)

        // when
        state = state.draw(CLOVER_TWO)

        // then
        assertThat(state).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `히트에서 버스트로 갈 수 있다`() {
        // given
        var state: State = FirstTurn().draw(CLOVER_KING).draw(CLOVER_NINE)

        // when
        state = state.draw(CLOVER_THREE)

        // then
        assertThat(state).isInstanceOf(Bust::class.java)
    }
}
