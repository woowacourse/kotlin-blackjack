package domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Shape
import blackjack.domain.dealer.Dealer
import blackjack.domain.dealer.DrawResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 들고있는 카드의 총합이 16이하라면 카드를 추가로 받는다`() {
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.TEN, Shape.DIAMOND),
                    Card(CardNumber.FIVE, Shape.SPADE)
                )
            )
        )

        assertThat(
            dealer.drawCard()
        ).isEqualTo(DrawResult.Success)
    }

    @Test
    fun `딜러는 들고있는 카드의 총합이 17이상이라면 카드를 추가로 받지 못한다`() {
        val dealer = Dealer(
            cards = Cards(
                listOf(
                    Card(CardNumber.TEN, Shape.DIAMOND),
                    Card(CardNumber.SEVEN, Shape.SPADE)
                )
            )
        )

        assertThat(
            dealer.drawCard()
        ).isEqualTo(DrawResult.Failure)
    }
}
