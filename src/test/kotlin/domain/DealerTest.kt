package domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.Shape
import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.ProfitMoney
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
        dealer.drawCard()

        assertThat(
            dealer.cards.isDrawnNothing
        ).isFalse
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
        dealer.drawCard()

        assertThat(
            dealer.cards.isDrawnNothing
        ).isTrue
    }

    @Test
    fun `플레이어가 총 10000의 이득을 보았을때 딜러는 10000의 손해를 본다`() {
        val dealer = Dealer()

        val actual = dealer.judgeDealerGameResults(ProfitMoney(10000))

        assertThat(actual).isEqualTo(ProfitMoney(-10000))
    }
}
