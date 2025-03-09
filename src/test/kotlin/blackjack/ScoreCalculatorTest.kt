package blackjack

import blackjack.model.ScoreCalculator
import blackjack.model.card.Card
import blackjack.model.card.Number
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `카드의 합계를 계산한다`() {
        val cards = listOf(Card(Shape.SPADE, Number.ACE), Card(Shape.SPADE, Number.SEVEN))
        val expected = 18

        val actual = ScoreCalculator.sum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A가 존재한다면 최적의 계산을 진행한다`() {
        val cards =
            listOf(
                Card(Shape.SPADE, Number.ACE),
                Card(Shape.DIAMOND, Number.ACE),
                Card(Shape.CLOVER, Number.ACE),
                Card(Shape.HEART, Number.EIGHT),
            )
        val expected = 21

        val actual = ScoreCalculator.calculateOptimalSum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `최종 계산은 합이 21보다 클 경우 이길 수 없는 수인 0을 반환한다`() {
        val cards =
            listOf(
                Card(Shape.CLOVER, Number.KING),
                Card(Shape.HEART, Number.JACK),
                Card(Shape.HEART, Number.QUEEN),
            )
        val expected = 0

        val actual = ScoreCalculator.calculateFinalScore(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `최종 계산은 합이 21보다 작거나 같은 경우 합을 반환한다`() {
        val cards =
            listOf(
                Card(Shape.SPADE, Number.ACE),
                Card(Shape.DIAMOND, Number.ACE),
                Card(Shape.CLOVER, Number.ACE),
                Card(Shape.HEART, Number.ACE),
            )
        val expected = 14

        val actual = ScoreCalculator.calculateFinalScore(cards)

        assertThat(actual).isEqualTo(expected)
    }
}
