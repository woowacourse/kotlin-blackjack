package blackjack.domain.state

import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_TEN
import blackjack.domain.state.Fixtures.CLOVER_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FirstTurnTest {
    @Test
    fun `첫턴에 블랙잭이 나올 수 있다 `() {
        var state: State = FirstTurn(CLOVER_TEN)
        state = state.draw(CLOVER_ACE)
        assertThat(state).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `첫턴에 히트가 나올 수 있다 `() {
        var state: State = FirstTurn(CLOVER_TEN)
        state = state.draw(CLOVER_TWO)
        assertThat(state).isInstanceOf(Hit::class.java)
    }
}
