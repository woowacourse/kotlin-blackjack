package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    fun `Card를 한 장 반환한다`() {
        val actual = CardDeck(Card.all()).drawCard()
        assertThat(Card.all()).contains(actual)
    }

    @Test
    fun `하나의 덱에서 53번을 뽑으면 오류가 발생한다`() {
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
}
