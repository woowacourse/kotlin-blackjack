package domain

import blackjack.domain.Card
import blackjack.domain.CardHand
import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.DrawResult
import blackjack.domain.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 들고있는 카드의 총합이 16이하라면 카드를 추가로 받는다`() {
        val dealer = createDealer(CardNumber.TEN, CardNumber.FIVE)

        assertThat(
            dealer.drawCard(card)
        ).isEqualTo(DrawResult.Success)
    }

    @Test
    fun `딜러는 들고있는 카드의 총합이 17이상이라면 카드를 추가로 받지 못한다`() {
        val dealer = createDealer(CardNumber.TEN, CardNumber.SEVEN)

        assertThat(
            dealer.drawCard(card)
        ).isEqualTo(DrawResult.Failure)
    }

    private val card = Card(CardNumber.ONE, Shape.SPADE)

    private fun createDealer(firstCardNumber: CardNumber, secondCardNumber: CardNumber): Dealer {
        return Dealer(CardHand(listOf(Card(firstCardNumber, Shape.SPADE), Card(secondCardNumber, Shape.SPADE))))
    }
}
