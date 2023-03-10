package blackjack.domain.state

import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class FirstTurnTest {
    @Test
    fun `첫 턴에 블랙잭이 나올 수 있다 `() {
        var state: State = FirstTurn().draw(CLOVER_KING)
        state = state.draw(CLOVER_ACE)
        assertThat(state).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `첫 턴에 히트가 나올 수 있다 `() {
        var state: State = FirstTurn().draw(CLOVER_KING)
        state = state.draw(CLOVER_TWO)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `첫 턴에 카드를 뽑을 수 있다`() {
        assertDoesNotThrow {
            FirstTurn().draw(CLOVER_KING).draw(CLOVER_ACE)
        }
    }

    @Test
    fun `첫 턴의 카드를 반환한다`() {
        val firstTurn = FirstTurn().draw(CLOVER_KING)
        assertThat(firstTurn.cards.toList()[0]).isEqualTo(CLOVER_KING)
    }

    @Test
    fun `첫 턴의 점수를 반환한다`() {
        val state: State = FirstTurn().draw(CLOVER_KING)
        assertThat(state.score.value).isEqualTo(10)
    }

    @Test
    fun `첫 턴의 카드 갯수를 반환한다`() {
        val state: State = FirstTurn().draw(CLOVER_KING).draw(CLOVER_ACE)
        assertThat(state.size).isEqualTo(2)
    }
}
