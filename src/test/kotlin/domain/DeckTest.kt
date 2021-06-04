package domain

import domain.card.Deck
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    internal fun name() {
        val deck = Deck()
        Assertions.assertThat(deck).hasSize(52)
        deck.pop()
        Assertions.assertThat(deck).hasSize(51)
    }
}
