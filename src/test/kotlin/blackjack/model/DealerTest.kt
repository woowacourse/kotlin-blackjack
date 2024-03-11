package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러는 첫번째 카드를 공개할 수 있다`() {
        val dealer = Dealer()
        val card = Card(Denomination.NINE, Suit.DIAMOND)

        dealer.draw(card)
        val firstDealerCard = dealer.openFirstCard()
        assertThat(firstDealerCard?.getCardSuit()).isEqualTo(card.getCardSuit())
        assertThat(firstDealerCard?.getCardDenomination()).isEqualTo(card.getCardDenomination())
    }

    @Test
    fun `딜러는 첫번째 카드를 공개할 수 없으면, null을 반환한다`() {
        val dealer = Dealer()
        assertThat(dealer.openFirstCard()).isEqualTo(null)
    }

    @Test
    fun `딜러는 손패의 합이 16을 초과하지 않으면 카드를 드로우 해야 한다`() {
        val drawableDealer = Dealer()
        drawableDealer.draw(Card(Denomination.FOUR, Suit.SPADE))
        drawableDealer.draw(Card(Denomination.FOUR, Suit.DIAMOND))
        drawableDealer.draw(Card(Denomination.FOUR, Suit.CLOVER))
        drawableDealer.draw(Card(Denomination.FOUR, Suit.HEART))
        assertThat(drawableDealer.checkDealerScoreCondition()).isTrue()
    }

    @Test
    fun `딜러는 손패의 합이 16을 초과할 때 카드를 더 이상 드로우할 수 없다`() {
        val unDrawableDealer = Dealer()
        unDrawableDealer.draw(Card(Denomination.QUEEN, Suit.SPADE))
        unDrawableDealer.draw(Card(Denomination.SEVEN, Suit.SPADE))
        assertThat(unDrawableDealer.checkDealerScoreCondition()).isFalse()
    }
}
