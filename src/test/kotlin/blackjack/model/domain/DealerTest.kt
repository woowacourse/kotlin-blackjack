package blackjack.model.domain

import blackjack.model.strategy.FalseShuffle
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private val dealer = Dealer()
    private val deck = Deck(FalseShuffle())

    @BeforeEach
    fun setup() {
        dealer.receiveCard(Card(Shape.Heart, CardNumber.Ace))
        dealer.receiveCard(Card(Shape.Spade, CardNumber.Six))
    }

    @Test
    fun `카드 숫자의 합을 계산한다`() {
        assertThat(dealer.sumCardNumber).isEqualTo(17)
    }

    @Test
    fun `받은 카드의 목록을 반환한다`() {
        assertThat(dealer.cardDeck).isEqualTo(listOf(Card(Shape.Heart, CardNumber.Ace), Card(Shape.Spade, CardNumber.Six)))
    }

    @Test
    fun `카드 숫자 합이 16보다 작으면 카드를 계속 받는다`() {
        dealer.drawUntilThreshold(deck)
        assertThat(dealer.sumCardNumber > 16).isTrue()
    }
}
