package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardBunchTest {
    @Test
    fun `카드뭉치 생성 시 다른 카드를 받으면 생성한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)

        assertDoesNotThrow { CardBunch(card1, card2) }
    }

    @Test
    fun `카드뭉치 생성 시 같은 카드를 받으면 에러를 발생시킨다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SIX)

        assertThrows<IllegalArgumentException> { CardBunch(card1, card2) }
    }

    @Test
    fun `카드뭉치 생성 시 2장의 카드만 받을 수 있다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)
        val card3 = Card(Shape.HEART, CardNumber.NINE)

        assertThrows<IllegalArgumentException> { CardBunch(card1, card2, card3) }
    }

    @Test
    fun `중복된 카드를 추가할시 에러를 발생시킨다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)
        val card3 = Card(Shape.HEART, CardNumber.SIX)

        val cardBunch = CardBunch(card1, card2)

        assertThrows<IllegalArgumentException> { cardBunch.addCard(card3) }
    }

    @Test
    fun `카드를 추가한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)
        val card3 = Card(Shape.HEART, CardNumber.NINE)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)

        assertThat(cardBunch.cards.size).isEqualTo(3)
    }
}
