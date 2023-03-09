package domain.gamer.state

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.Dealer
import domain.gamer.cards.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val card = Card(Shape.HEART, CardValue.EIGHT)
        val dealer = Dealer(Cards(listOf()))
        dealer.pickCard(card)
        assertThat(dealer.cards.getCards()).isEqualTo(listOf(card))
    }

    @Test
    fun `딜러 카드의 합이 16을 넘었을 경우 카트를 뽑을 수 없다`() {
        val dealer =
            dealerForm(
                listOf(
                    Card(Shape.SPADE, CardValue.JACK),
                    Card(Shape.SPADE, CardValue.JACK)
                )
            )
        assertThat(dealer.checkAvailableForPick()).isFalse
    }

    @Test
    fun `딜러 카드의 합이 16을 넘지 않았을 경우 카드를 뽑을 수 있다`() {
        val dealer = dealerForm(listOf(Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealer.checkAvailableForPick()).isTrue
    }

    private fun dealerForm(cards: List<Card>): Dealer {
        return Dealer(Cards(cards))
    }
}
