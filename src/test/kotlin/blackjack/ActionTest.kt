package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Deck
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.state.Action
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ActionTest {
    @Test
    fun `hit Hand에 카드 1장 추가`() {
        val cards = listOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val beforeHand = Hand(cards)
        val deck = Deck()
        val action = Action(beforeHand, deck)
        val afterHand = action.hit()

        assertThat(afterHand.cards.size).isEqualTo(3)
    }
}
