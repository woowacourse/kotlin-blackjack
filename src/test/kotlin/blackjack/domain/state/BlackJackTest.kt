package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_ACE
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackTest {
    @Test
    fun `블랙잭은 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { BlackJack(Cards(CLOVER_KING, CLOVER_ACE)).draw(CLOVER_KING) }
    }

    @Test
    fun `블랙잭의 점수는 21점이다`() {
        val state: State = BlackJack(Cards(CLOVER_KING, CLOVER_ACE))
        assertThat(state.score.value).isEqualTo(21)
    }

    @Test
    fun `블랙잭의 점수가 21이 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { BlackJack(Cards(CLOVER_KING, CLOVER_QUEEN)) }
    }

    @Test
    fun `블랙잭의 카드 개수는 2장이다`() {
        val state: State = BlackJack(Cards(CLOVER_KING, CLOVER_ACE))
        assertThat(state.size).isEqualTo(2)
    }

    @Test
    fun `블랙잭의 카드 개수는 2장이 아니면 에러가 발생한다`() {
        assertThrows<IllegalArgumentException> { BlackJack(Cards(CLOVER_KING, CLOVER_QUEEN, CLOVER_ACE)) }
    }
}
