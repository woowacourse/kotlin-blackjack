package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `카드들에서 카드를 반환한다`() {
        val cards = Deck(listOf(Card(Suit.HEART, Rank.SIX)))
        assertThat(cards.draw(1)).isEqualTo(listOf(Card(Suit.HEART, Rank.SIX)))
    }
}
