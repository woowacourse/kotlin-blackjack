package domain.result

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `A를 포함하고 점수에 10을 더 했을 때 21보다 작으면 점수에 10을 더한다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.JACK), Card(CardShape.HEART, CardNumber.ACE))

        val score = Score.of(hand)

        assertThat(score.value).isEqualTo(21)
    }

    @Test
    fun `A를 포함하고 점수에 10을 더 했을 때 21보다 크면 점수가 그대로이다`() {
        val hand = Hand(
            Card(CardShape.HEART, CardNumber.JACK),
            Card(CardShape.HEART, CardNumber.FIVE),
            Card(CardShape.HEART, CardNumber.ACE),
        )

        val score = Score.of(hand)

        assertThat(score.value).isEqualTo(16)
    }

    @Test
    fun `A를 포함하지 않으면 점수가 그대로이다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.THREE), Card(CardShape.HEART, CardNumber.TWO))

        val score = Score.of(hand)

        assertThat(score.value).isEqualTo(5)
    }

    @Test
    fun `other 보다 점수가 크면 true 를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.JACK), Card(CardShape.HEART, CardNumber.ACE))

        val score = Score.of(hand)

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.THREE), Card(CardShape.HEART, CardNumber.ACE))

        val otherScore = Score.of(otherHand)

        assertThat(score.isBiggerThan(otherScore)).isTrue
    }

    @Test
    fun `other 보다 크지 않으면 false 를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.JACK), Card(CardShape.HEART, CardNumber.ACE))

        val score = Score.of(hand)

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.JACK), Card(CardShape.HEART, CardNumber.ACE))

        val otherScore = Score.of(otherHand)

        assertThat(score.isBiggerThan(otherScore)).isFalse
    }
    @Test
    fun `other 과 같으면 true 를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.JACK), Card(CardShape.HEART, CardNumber.KING))

        val biggerScore = Score.of(hand)

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.JACK), Card(CardShape.HEART, CardNumber.KING))

        val otherScore = Score.of(otherHand)

        assertThat(biggerScore.isSame(otherScore)).isTrue
    }
}
