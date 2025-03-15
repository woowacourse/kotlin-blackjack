package blackjack.domain.model

import blackjack.Fixtures.HEART_ACE
import blackjack.domain.model.card.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 카드를 반환한다`() {
        val deck = Deck(listOf(HEART_ACE))
        assertThat(deck.draw(1)).isEqualTo(listOf(HEART_ACE))
    }
}
