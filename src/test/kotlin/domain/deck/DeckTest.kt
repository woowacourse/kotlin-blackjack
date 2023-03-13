package domain.deck

import domain.SpadeCardsOf
import domain.card.Card
import domain.card.CardValue.ACE
import domain.card.CardValue.FIVE
import domain.card.CardValue.SEVEN
import domain.card.CardValue.SIX
import domain.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class DeckTest {

    @Test
    fun `게임 덱에서 보유한 카드를 한 장 준다`() {
        val deck = Deck(listOf(Card(Shape.SPADE, ACE)))
        assertThat(deck.giveCard()).isEqualTo(Card(Shape.SPADE, ACE))
    }

    @Test
    fun `카드를 보유하지 않은 덱을 생성하면 IllegalStateException이 발생한다`() {
        assertThrows<IllegalStateException> {
            Deck(listOf())
        }
    }

    @Test
    fun `새로운 카드들을 덱에 저장한다`() {
        val deck = Deck((SpadeCardsOf(FIVE, SIX, SEVEN).getCards()))
        assertAll(
            {
                assertThat(deck.giveCard()).isEqualTo(Card(Shape.SPADE, FIVE))
            },
            {
                assertThat(deck.giveCard()).isEqualTo(Card(Shape.SPADE, SIX))
            },
            {
                assertThat(deck.giveCard()).isEqualTo(Card(Shape.SPADE, SEVEN))
            }
        )
    }
}
