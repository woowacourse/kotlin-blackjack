package blackjackgame.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    internal fun createDeck() {
        val deck = Deck()
        val distinctCards: Set<Card> = deck.cards.toSet()

        assertThat(deck.cards).hasSize(52)
        assertThat(deck.cards).hasSize(distinctCards.size)
    }

    @Test
    internal fun handOutInitialCards() {
        val deck = Deck()
        val cards = deck.handOutInitialCards()

        assertThat(deck.cards).hasSize(50)
        assertThat(cards).hasSize(2)
    }

    @Test
    internal fun handOutCard() {
        val deck = Deck()
        val card = deck.handOutCard()

        assertThat(deck.cards).hasSize(51)
        assertThat(card).isNotNull
    }
}