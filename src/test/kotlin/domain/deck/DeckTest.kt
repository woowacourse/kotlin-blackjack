package domain.deck

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {

    @Test
    fun `게임 덱에서 보유한 카드를 한 장 준다`() {
        val deck = Deck(listOf(Card(Shape.SPADE, CardValue.ACE)))
        assertThat(deck.giveCard()).isEqualTo(Card(Shape.SPADE, CardValue.ACE))
    }

    @Test
    fun `카드를 보유하지 않는 게임 덱에서 카드를 주면 IllegalStateException이 발생한다`() {
        val deck = Deck(listOf())
        assertThrows<IllegalStateException> {
            deck.giveCard()
        }
    }
}
