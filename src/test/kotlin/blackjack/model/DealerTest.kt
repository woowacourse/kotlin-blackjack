package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 뽑은 카드를 갖는다`() {
        val dealer = Dealer()
        val cards = Card.of(listOf(Pair(Pattern.HEART, CardNumber.TWO)))
        val card = cards.last()
        val gameDeck = GameDeck()
        gameDeck.resetDeck(cards)
        dealer.addCard(gameDeck.drawCard())

        assertThat(dealer.getState().hand().cards.last()).isEqualTo(card)
    }

    @Test
    fun `딜러가 뽑은 카드의 총 합은 16을 넘어야한다`() {
        val dealer = Dealer()
        val cards = Card.of(listOf(Pair(Pattern.HEART, CardNumber.KING), Pair(Pattern.HEART, CardNumber.ACE)))
        val gameDeck = GameDeck()

        gameDeck.resetDeck(cards)
        dealer.addCard(gameDeck.drawCard())
        dealer.addCard(gameDeck.drawCard())
        println(dealer.getState().hand().calculate())
        assertThat(dealer.getState().hand().calculate() > Dealer.DEALER_CARD_DRAW_THRESHOLD).isTrue()
    }
}
