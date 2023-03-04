package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16보다 작을경우 카드를 받을 수 있다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch)

        assertThat(dealer.canGetCard()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 17보다 클경우 카드를 받을 수 없다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val card3 = Card(Shape.HEART, CardNumber.NINE)
        val cardBunch = CardBunch(card1, card2, card3)
        val dealer = Dealer(cardBunch)

        assertThat(dealer.canGetCard()).isFalse
    }

    @Test
    fun `딜러가 승리한다`() {
        val card1 = Card(Shape.HEART, CardNumber.JACK)
        val card2 = Card(Shape.HEART, CardNumber.KING)
        val cardBunch1 = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch1)

        val card3 = Card(Shape.HEART, CardNumber.SIX)
        val card4 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch2 = CardBunch(card3, card4)
        val player = Player("krrong", cardBunch2)

        dealer.compareScore(player)

        assertThat(player.consequence).isEqualTo(Consequence.LOSE)
    }
}
