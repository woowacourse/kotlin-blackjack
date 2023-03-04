package blackjack.domain

import blackjack.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class CardBunchTest {
    @Test
    fun `카드 묶음을 생성한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)

        assertDoesNotThrow { CardBunch(card1, card2) }
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

    @Test
    fun `6,7,9 카드의 총합을 계산한다`() {
        val card1 = Card(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card(Shape.HEART, CardNumber.SIX)
        val card3 = Card(Shape.HEART, CardNumber.NINE)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)

        val totalScore = cardBunch.getTotalScore()
        assertThat(totalScore).isEqualTo(22)
    }

    @Test
    fun `Ace,2,3 카드의 총합을 계산한다`() {
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.TWO)
        val card3 = Card(Shape.HEART, CardNumber.THREE)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)

        val totalScore = cardBunch.getTotalScore()
        assertThat(totalScore).isEqualTo(16)
    }

    @Test
    fun `Ace,Jack,King 카드의 총합을 계산한다`() {
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val card3 = Card(Shape.HEART, CardNumber.KING)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)

        val totalScore = cardBunch.getTotalScore()
        assertThat(totalScore).isEqualTo(21)
    }

    @Test
    fun `카드의 총합이 21이 넘었다면 True를 반환한다`() {
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val card3 = Card(Shape.HEART, CardNumber.KING)
        val card4 = Card(Shape.HEART, CardNumber.TWO)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)
        cardBunch.addCard(card4)

        assertThat(cardBunch.isBurst()).isTrue
    }

    @Test
    fun `카드의 총합이 21이 넘지 않았다면 False를 반환한다`() {
        val card1 = Card(Shape.HEART, CardNumber.ACE)
        val card2 = Card(Shape.HEART, CardNumber.JACK)
        val card3 = Card(Shape.HEART, CardNumber.KING)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)

        assertThat(cardBunch.isBurst()).isFalse
    }
}
