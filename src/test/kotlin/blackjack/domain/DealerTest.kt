package blackjack.domain

import blackjack.Shape
import blackjack.domain.state.DealerFirstTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러 카드의 합이 16이하인지 판단한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SEVEN)
        val state = DealerFirstTurn(CardBunch(card1)).draw(card2)
        val dealer = Dealer(state)

        assertThat(dealer.isOverCondition()).isFalse
    }
}
