package domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    internal fun name() {
        val deck = Deck()
        Assertions.assertThat(deck.cards).hasSize(52)
        deck.draw()
        Assertions.assertThat(deck.cards).hasSize(51)
    }
}