package blackjack.domain.state

import blackjack.domain.card.Cards
import blackjack.domain.state.Fixtures.CLOVER_JACK
import blackjack.domain.state.Fixtures.CLOVER_KING
import blackjack.domain.state.Fixtures.CLOVER_QUEEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustTest {
    @Test
    fun `버스트 더이상 카드를 뽑을 수 없다`() {
        assertThrows<IllegalStateException> { Bust(Cards()).draw(CLOVER_KING) }
    }

    @Test
    fun `버스트의 카드를 반환한다`() {
        val cards = listOf(CLOVER_KING, CLOVER_QUEEN, CLOVER_JACK)
        assertThat(Bust(Cards(cards.toSet())).cards.toList()).isEqualTo(cards)
    }

    @Test
    fun `버스트의 점수를 반환한다`() {
        val state: State = Bust(Cards(setOf(CLOVER_KING, CLOVER_QUEEN, CLOVER_JACK)))
        assertThat(state.score.value).isEqualTo(30)
    }

    @Test
    fun `버스트의 카드 갯수를 반환한다`() {
        val state: State = Bust(Cards((setOf(CLOVER_KING, CLOVER_QUEEN, CLOVER_JACK))))
        assertThat(state.size).isEqualTo(3)
    }
}
