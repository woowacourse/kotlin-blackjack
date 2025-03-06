package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards

    fun getCard(card: Card) {
        _cards.add(card)
    }
}

class HandTest {
    @Test
    fun `갖고 있는 카드를 확인할 수 있다`() {
        val hand = Hand()
        val card = Card(Ace(), Suit.SPADE)
        hand.getCard(card)
        assertThat(hand.cards).contains(card)
    }
}
