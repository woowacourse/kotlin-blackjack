package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HandTest {
    @Test
    fun `Hand 객체 생성`() {
        val cards = listOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        assertThat(hand.cards).isEqualTo(cards)
    }

    @Test
    fun `2장 이상 존재해야함`() {
        val cards = listOf(Card(Denomination.ACE, Suit.HEARTS))
        assertThrows<IllegalArgumentException> { Hand(cards) }
    }
}
