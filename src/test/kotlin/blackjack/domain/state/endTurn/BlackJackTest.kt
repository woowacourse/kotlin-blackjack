package blackjack.domain.state.endTurn

import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import blackjack.domain.state.State
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackTest {
    @Test
    fun `블랙잭은 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { BlackJack().draw(CLOVER_KING) }
    }

    @Test
    fun `블랙잭의 카드를 반환한다`() {
        val cards = listOf(CLOVER_KING, CLOVER_QUEEN)
        assertThat(BlackJack(cards).cards.toList()).isEqualTo(cards)
    }

    @Test
    fun `블랙잭의 점수를 반환한다`() {
        val state: State = BlackJack(CLOVER_KING, CLOVER_ACE)
        assertThat(state.score.value).isEqualTo(21)
    }

    @Test
    fun `블랙잭의 카드 갯수를 반환한다`() {
        val state: State = BlackJack(CLOVER_KING, CLOVER_ACE)
        assertThat(state.size).isEqualTo(2)
    }
}
