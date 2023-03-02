package domain.gamer

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `뽑은 카드를 저장한다`() {
        val card = Card(Shape.HEART, CardValue.EIGHT)
        val dealer = Dealer()
        dealer.pickCard(card)
        assertThat(dealer.cards).isEqualTo(listOf(card))
    }

    @Test
    fun `카드 값의 합을 반환한다`() {
        val dealer = Dealer(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealer.calculateCardSum()).isEqualTo(20)
    }

    @Test
    fun `딜러 카드의 합이 16을 넘었을 경우 true를 반환한다`() {
        val dealer = Dealer(mutableListOf(Card(Shape.SPADE, CardValue.JACK), Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealer.checkAvailableForPick()).isTrue
    }

    @Test
    fun `딜러 카드의 합이 16을 넘지 않았을 경우 false 를 반환한다`() {
        val dealer = Dealer(mutableListOf(Card(Shape.SPADE, CardValue.JACK)))
        assertThat(dealer.checkAvailableForPick()).isFalse
    }
}
