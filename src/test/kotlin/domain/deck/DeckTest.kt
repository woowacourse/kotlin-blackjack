package domain.deck

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {
    @Test
    fun `덱에서 마지막 카드를 준다`() {
        val deck = Deck(listOf(Card(Shape.SPADE, CardValue.TWO), Card(Shape.SPADE, CardValue.THREE)))
        assertThat(deck.giveCard()).isEqualTo(Card(Shape.SPADE, CardValue.THREE))
    }
}
