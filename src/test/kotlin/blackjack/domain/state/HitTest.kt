package blackjack.domain.state

import blackjack.domain.state.Fixtures.CLOVER_FOUR
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_NINE
import blackjack.domain.state.Fixtures.CLOVER_THREE
import blackjack.domain.state.Fixtures.CLOVER_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `히트에서 히트로 갈 수 있다`() {
        // given
        var state: State = FirstTurn(CLOVER_TWO)
        state = state.draw(CLOVER_THREE)

        // when
        state = state.draw(CLOVER_FOUR)

        // then
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `히트에서 블랙잭으로 갈 수 있다`() {
        // given
        var state: State = FirstTurn(CLOVER_KING)
        state = state.draw(CLOVER_NINE)

        // when
        state = state.draw(CLOVER_TWO)

        // then
        assertThat(state).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `히트에서 버스트로 갈 수 있다`() {
        // given
        var state: State = FirstTurn(CLOVER_KING)
        state = state.draw(CLOVER_NINE)

        // when
        state = state.draw(CLOVER_THREE)

        // then
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `히트에서 스테이로 갈 수 있다`() {
        // given
        var state: State = FirstTurn(CLOVER_KING)
        state = state.draw(CLOVER_NINE)

        // when
        state = state.draw(CLOVER_THREE)

        // then
        assertThat(state).isInstanceOf(Bust::class.java)
    }
}
