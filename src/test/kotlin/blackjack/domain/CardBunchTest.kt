package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardBunchTest {
    @Test
    fun `카드 묶음을 생성한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card.get(Shape.HEART, CardNumber.SIX)

        assertDoesNotThrow { CardBunch(card1, card2) }
    }

    @Test
    fun `카드를 추가한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.SEVEN)
        val card2 = Card.get(Shape.HEART, CardNumber.SIX)
        val card3 = Card.get(Shape.HEART, CardNumber.NINE)

        val cardBunch = CardBunch(card1, card2)
        cardBunch.addCard(card3)

        assertThat(cardBunch.cards.size).isEqualTo(3)
    }

    @Test
    fun `카드의 총합이 21이 넘었다면 isBurst함수가 true를 반환한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.ACE)
        val card2 = Card.get(Shape.HEART, CardNumber.JACK)
        val card3 = Card.get(Shape.HEART, CardNumber.KING)
        val card4 = Card.get(Shape.HEART, CardNumber.TWO)

        val cardBunch = CardBunch(card1, card2, card3, card4)

        val totalScore = cardBunch.getSumOfCards()
        assertThat(totalScore).isEqualTo(23)
        assertThat(cardBunch.isBurst()).isTrue
    }

    @Test
    fun `카드의 총합이 21이 넘었다면 isBurst함수가 false를 반환한다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.ACE)
        val card2 = Card.get(Shape.HEART, CardNumber.JACK)
        val card3 = Card.get(Shape.HEART, CardNumber.KING)

        val cardBunch = CardBunch(card1, card2, card3)

        assertThat(cardBunch.isBurst()).isFalse
    }

    @Test
    fun `ACE카드가 4장이면 합계는 14다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.ACE)
        val card2 = Card.get(Shape.SPADE, CardNumber.ACE)
        val card3 = Card.get(Shape.DIAMOND, CardNumber.ACE)
        val card4 = Card.get(Shape.CLOVER, CardNumber.ACE)

        val cardBunch = CardBunch(card1, card2, card3, card4)

        assertThat(cardBunch.getSumOfCards()).isEqualTo(14)
    }

    @Test
    fun `ACE카드가 2장이면 합계는 12다`() {
        val card1 = Card.get(Shape.HEART, CardNumber.ACE)
        val card2 = Card.get(Shape.SPADE, CardNumber.ACE)

        val cardBunch = CardBunch(card1, card2)

        assertThat(cardBunch.getSumOfCards()).isEqualTo(12)
    }

    @ParameterizedTest(name = "카드의 합은 {3}이다")
    @MethodSource("provideCards")
    fun `합계 테스트`(card1: Card, card2: Card, card3: Card, sum: Int) {
        val cardBunch = CardBunch(card1, card2, card3)
        assertThat(cardBunch.getSumOfCards()).isEqualTo(sum)
    }

    companion object {
        @JvmStatic
        fun provideCards(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Card.get(Shape.HEART, CardNumber.SIX),
                    Card.get(Shape.HEART, CardNumber.SEVEN),
                    Card.get(Shape.HEART, CardNumber.NINE),
                    22
                ),
                Arguments.of(
                    Card.get(Shape.HEART, CardNumber.ACE),
                    Card.get(Shape.HEART, CardNumber.JACK),
                    Card.get(Shape.HEART, CardNumber.KING),
                    21
                ),
                Arguments.of(
                    Card.get(Shape.HEART, CardNumber.ACE),
                    Card.get(Shape.HEART, CardNumber.TWO),
                    Card.get(Shape.HEART, CardNumber.THREE),
                    16
                ),
            )
        }
    }
}
