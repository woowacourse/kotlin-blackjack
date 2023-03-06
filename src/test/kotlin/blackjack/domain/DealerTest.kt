package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16이하인지 판단한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val cardBunch = CardBunch(card1, card2)
        val dealer = Dealer(cardBunch)

        assertThat(dealer.isOverCondition()).isFalse
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

        assertThat(player.chooseWinner(dealer)).isEqualTo(Consequence.LOSE)
    }
}
