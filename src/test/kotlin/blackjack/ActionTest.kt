package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.Action
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ActionTest {
    @Test
    fun `hit 카드 추가 확인`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        val deck = Deck()
        val action = Action(hand, deck)
        action.hit()
        action.hit()
        assertThat(hand.cards.size).isEqualTo(4)
    }

    @Test
    fun `Running 확인`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        val deck = Deck()
        val action = Action(hand, deck)
        assertThat(action.hit()).isTrue
    }

    @Test
    fun `Finished 확인`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        val deck = Deck()
        val action = Action(hand, deck)
        assertThat(action.stay()).isFalse
    }
}
