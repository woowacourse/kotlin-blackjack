package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `덱에 카드가 없을 때 뽑으면 오류가 난다`() {
        val cardDeck = CardDeck(Card.all())
        repeat(52) {
            cardDeck.drawCard()
        }
        assertThrows<IllegalArgumentException> { cardDeck.drawCard() }
    }

    @Test
    fun `하트 에이스를 한 장 뽑는다`() {
        // given
        val card = Card.get(Shape.HEART, CardNumber.ACE)
        val cardDeck = CardDeck(mutableListOf(card))

        // when
        val actual = cardDeck.drawCard()

        // then
        val expected = Card.get(Shape.HEART, CardNumber.ACE)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `2장의 카드를 뽑는다`() {
        // given
        val cardDeck =
            CardDeck(mutableListOf(Card.get(Shape.HEART, CardNumber.ACE), Card.get(Shape.HEART, CardNumber.TWO)))

        // when
        val actual = cardDeck.drawTwoCards()

        // then
        assertThat(actual.size).isEqualTo(2)
    }
}
