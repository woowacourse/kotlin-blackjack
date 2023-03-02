package blackjack.domain

import blackjack.Shape
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class CardBunchTest {
    @Test
    fun `카드뭉치 생성 시 다른 카드를 받으면 생성한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)

        assertDoesNotThrow { CardBunch(setOf(card1, card2)) }
    }

    @Test
    fun `카드뭉치 생성 시 같은 카드를 받으면 에러를 발생시킨다`() {
        val card1 = Card(Shape.HEART, CardNumber.SIX)
        val card2 = Card(Shape.HEART, CardNumber.SIX)

        assertThrows<IllegalArgumentException> { CardBunch(setOf(card1, card2)) }
    }

    @Test
    fun `카드뭉치 생성 시 2장의 카드만 받을 수 있다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)
        val card3 = Card(Shape.HEART, CardNumber.NINE)

        assertThrows<IllegalArgumentException> { CardBunch(setOf(card1, card2, card3)) }
    }
}
