package domain.card

import model.domain.card.Card
import model.domain.card.Hand
import model.tools.CardNumber
import model.tools.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class HandTest {

    @Test
    fun `딜러가 하트9,하트2를 소유하고 있을 경우, 17을 넘지 않으므로, true이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.NINE),
                Card.of(Shape.HEARTS, CardNumber.TWO),
            ),
        )

        // when
        val actual = hand.dealerIsNotOver17()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `손패 1장에서, 하트3 카드 1장을 추가로 받아 손패는 2장이다`() {
        // given
        val card = Card.of(Shape.HEARTS, CardNumber.NINE)
        val newCard = Card.of(Shape.HEARTS, CardNumber.THREE)
        val hand = Hand(card)

        // when
        hand.drawOneCard(newCard)
        val actual = hand.cards

        // then
        assertAll(
            { assertThat(actual.size).isEqualTo(2) },
            { assertThat(actual.last()).isEqualTo(Card.of(Shape.HEARTS, CardNumber.THREE)) },
        )
    }

    @Test
    fun `카드를 추가로 드로우했으나, 21점을 넘겨 Bust 상태이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.NINE),
                Card.of(Shape.HEARTS, CardNumber.TEN),
            ),
        )
        val newCard = Card.of(Shape.HEARTS, CardNumber.FIVE)

        // when
        hand.drawOneCard(newCard)
        val actual = hand.isBust()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `게임 시작단계에서, 카드 2장을 받아 블랙잭 상태이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.ACE),
                Card.of(Shape.HEARTS, CardNumber.KING),
            ),
        )

        // when
        val actual = hand.isBlackJack()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `하트7,하트8을 들고있으며, 합은 15이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.SEVEN),
                Card.of(Shape.HEARTS, CardNumber.EIGHT),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(15)
    }

    @Test
    fun `하트A,하트3을 들고있으며, 합은 14이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.ACE),
                Card.of(Shape.HEARTS, CardNumber.THREE),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(14)
    }

    @Test
    fun `하트A,하트K을 들고있으며, 합은 21이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.ACE),
                Card.of(Shape.HEARTS, CardNumber.KING),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(21)
    }

    @Test
    fun `하트A,클로버A 들고있으며, 합은 12이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.ACE),
                Card.of(Shape.CLUBS, CardNumber.ACE),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(12)
    }

    @Test
    fun `하트7,하트A,클로버A 들고있으며, 합은 19이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.SEVEN),
                Card.of(Shape.HEARTS, CardNumber.ACE),
                Card.of(Shape.CLUBS, CardNumber.ACE),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(19)
    }

    @Test
    fun `하트7,하트10,하트A를 들고있으며, 합은 18이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.HEARTS, CardNumber.SEVEN),
                Card.of(Shape.HEARTS, CardNumber.TEN),
                Card.of(Shape.HEARTS, CardNumber.ACE),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(18)
    }

    @Test
    fun `스페이드A,클로버A,하트A를 들고있으며, 합은 13이다`() {
        // given
        val hand = Hand(
            listOf(
                Card.of(Shape.SPADES, CardNumber.ACE),
                Card.of(Shape.CLUBS, CardNumber.ACE),
                Card.of(Shape.HEARTS, CardNumber.ACE),
            ),
        )

        // when
        val actual = hand.getTotalScoreWithAceCard()

        // then
        assertThat(actual).isEqualTo(13)
    }
}
