package blackjack

import blackjack.CardFixture.Companion.CLOVER_ACE
import blackjack.CardFixture.Companion.DIAMOND_ACE
import blackjack.CardFixture.Companion.HEART_ACE
import blackjack.CardFixture.Companion.HEART_EIGHT
import blackjack.CardFixture.Companion.HEART_JACK
import blackjack.CardFixture.Companion.HEART_KING
import blackjack.CardFixture.Companion.HEART_QUEEN
import blackjack.CardFixture.Companion.HEART_SEVEN
import blackjack.CardFixture.Companion.SPADE_ACE
import blackjack.model.ScoreCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `카드의 합계를 계산한다`() {
        val cards = listOf(HEART_ACE, HEART_SEVEN)
        val expected = 18

        val actual = ScoreCalculator.sum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A가 존재한다면 최적의 계산을 진행한다`() {
        val cards = listOf(HEART_ACE, CLOVER_ACE, SPADE_ACE, HEART_EIGHT)
        val expected = 21

        val actual = ScoreCalculator.calculateOptimalSum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `최종 계산은 합이 21보다 클 경우 이길 수 없는 수인 0을 반환한다`() {
        val cards = listOf(HEART_JACK, HEART_QUEEN, HEART_KING)
        val expected = 0

        val actual = ScoreCalculator.calculateFinalScore(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `최종 계산은 합이 21보다 작거나 같은 경우 합을 반환한다`() {
        val cards = listOf(HEART_ACE, CLOVER_ACE, SPADE_ACE, DIAMOND_ACE)
        val expected = 14

        val actual = ScoreCalculator.calculateFinalScore(cards)

        assertThat(actual).isEqualTo(expected)
    }
}
