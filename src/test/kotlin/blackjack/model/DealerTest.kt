package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 뽑은 카드를 갖는다`() {
        val dealer = Dealer()
        val card = Card(Pattern.HEART, CardNumber.ACE)

        dealer.takeCard(card)
        assertThat(dealer.state.hand.cards).contains(card)
    }

    @Test
    fun `딜러가 뽑은 카드의 총 합은 16을 넘어야한다`() {
        val dealer = Dealer()
        val gameDeck = GameDeck()

        dealer.drawUntilOverThreshold(gameDeck)
        assertThat(dealer.state.hand.calculate() > Dealer.THRESHOLD).isTrue()
    }
}
