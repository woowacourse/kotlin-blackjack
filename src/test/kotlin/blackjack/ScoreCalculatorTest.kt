package blackjack

import blackjack.model.ScoreCalculator
import blackjack.model.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `카드의 합계를 계산한다`() {
        val cards =
            CardFixture.combine(
                CardFixture.createCards(1, CardNumber.ACE),
                CardFixture.createCards(1, CardNumber.SEVEN),
            )
        val expected = 18

        val actual = ScoreCalculator.sum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A가 존재한다면 최적의 계산을 진행한다`() {
        val cards =
            CardFixture.combine(
                CardFixture.createCards(3, CardNumber.ACE),
                CardFixture.createCards(1, CardNumber.EIGHT),
            )
        val expected = 21

        val actual = ScoreCalculator.calculateOptimalSum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `최종 계산은 합이 21보다 클 경우 이길 수 없는 수인 0을 반환한다`() {
        val cards =
            CardFixture.combine(
                CardFixture.createCards(1, CardNumber.JACK),
                CardFixture.createCards(1, CardNumber.QUEEN),
                CardFixture.createCards(1, CardNumber.KING),
            )
        val expected = 0

        val actual = ScoreCalculator.calculateFinalScore(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `최종 계산은 합이 21보다 작거나 같은 경우 합을 반환한다`() {
        val cards =
            CardFixture.combine(
                CardFixture.createCards(4, CardNumber.ACE),
            )
        val expected = 14

        val actual = ScoreCalculator.calculateFinalScore(cards)

        assertThat(actual).isEqualTo(expected)
    }
}
