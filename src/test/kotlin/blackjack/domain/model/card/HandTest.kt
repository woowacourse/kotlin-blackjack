package blackjack.domain.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `손패에 카드를 추가할 수 있다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.ACE, Suit.CLUB),
            )
        val actualCardsList = hand.toList()

        val expectedSize = 1

        assertThat(actualCardsList).hasSize(expectedSize)
    }

    @Test
    fun `손패에 카드 목록을 확인할 수 있다`() {
        val queenHeart = Card.of(CardNumber.QUEEN, Suit.HEART)
        val aceSpade = Card.of(CardNumber.ACE, Suit.SPADE)
        val hand: Hand =
            Hand.of(
                queenHeart,
                aceSpade,
            )
        val actualCardList = hand.toList()

        val expectedCardList = listOf(queenHeart, aceSpade)

        assertThat(actualCardList).isEqualTo(expectedCardList)
    }

    @Test
    fun `손패에 카드 값이 21이 넘으면 버스트인지 알 수 있다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.TEN, Suit.HEART),
                Card.of(CardNumber.KING, Suit.SPADE),
                Card.of(CardNumber.TWO, Suit.CLUB),
            )
        val actualIsBust = hand.isBust()

        val expected = true

        assertThat(actualIsBust).isEqualTo(expected)
    }

    @Test
    fun `손패에 카드 값이 21이 넘지 않으면 버스트가 아닌지 알 수 있다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.TEN, Suit.HEART),
                Card.of(CardNumber.TWO, Suit.CLUB),
            )
        val actualIsBust = hand.isBust()

        val expected = false

        assertThat(actualIsBust).isEqualTo(expected)
    }

    @Test
    fun `손패에 카드 값이 특정한 값보다 이하인지 알 수 있다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.TEN, Suit.SPADE),
                Card.of(CardNumber.SIX, Suit.CLUB),
            )
        val actualIsLessOrSameThanSixteen = hand.isLessOrSameThan(16)

        val expected = true

        assertThat(actualIsLessOrSameThanSixteen).isEqualTo(expected)
    }

    @Test
    fun `손패에 ACE 하나와 KING이 하나 있으면 21점이다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.ACE, Suit.SPADE),
                Card.of(CardNumber.KING, Suit.CLUB),
            )
        val actualScore = hand.getScore()

        val expectedScore = 21

        assertThat(actualScore).isEqualTo(expectedScore)
    }

    @Test
    fun `손패에 ACE 2개와 KING이 하나 있으면 12점이다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.ACE, Suit.SPADE),
                Card.of(CardNumber.ACE, Suit.HEART),
                Card.of(CardNumber.KING, Suit.CLUB),
            )
        val actualScore = hand.getScore()

        val expectedScore = 12

        assertThat(actualScore).isEqualTo(expectedScore)
    }

    @Test
    fun `손패에 5 하나와 JACK이 하나 있으면 15점이다`() {
        val hand: Hand =
            Hand.of(
                Card.of(CardNumber.FIVE, Suit.SPADE),
                Card.of(CardNumber.JACK, Suit.HEART),
            )
        val actualScore = hand.getScore()

        val expectedScore = 15

        assertThat(actualScore).isEqualTo(expectedScore)
    }
}
