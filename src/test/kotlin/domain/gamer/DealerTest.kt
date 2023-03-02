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
}
