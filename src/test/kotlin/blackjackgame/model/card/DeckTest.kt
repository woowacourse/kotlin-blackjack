package blackjackgame.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest{
    @Test
    internal fun createDeck() {
        val deck = Deck()
        val distinctCards: Set<Card> = deck.cards.toSet()

        assertThat(deck.cards).hasSize(52)
        assertThat(deck.cards).hasSize(distinctCards.size)
    }
}