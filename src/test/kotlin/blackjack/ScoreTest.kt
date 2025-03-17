package blackjack

import blackjack.CardFixture.Companion.CLOVER_ACE
import blackjack.CardFixture.Companion.CLOVER_JACK
import blackjack.CardFixture.Companion.HEART_ACE
import blackjack.CardFixture.Companion.HEART_EIGHT
import blackjack.CardFixture.Companion.HEART_FIVE
import blackjack.CardFixture.Companion.HEART_JACK
import blackjack.CardFixture.Companion.HEART_QUEEN
import blackjack.CardFixture.Companion.HEART_SEVEN
import blackjack.CardFixture.Companion.SPADE_ACE
import blackjack.model.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `카드의 합계가 버스트 숫자를 만족하는 지 판단한다`() {
        val cards = listOf(HEART_ACE, HEART_SEVEN)
        val bustedCards = listOf(HEART_JACK, CLOVER_JACK, HEART_QUEEN)

        val cardsScore = Score.optimizedSum(cards)
        val bustedCardsScore = Score.optimizedSum(bustedCards)

        assertFalse(cardsScore.isBust())
        assertTrue(bustedCardsScore.isBust())
    }

    @Test
    fun `카드의 합계가 블랙잭 숫자를 만족하는 지 판단한다`() {
        val cards = listOf(HEART_ACE, HEART_SEVEN)
        val blackjackCards = listOf(HEART_JACK, HEART_ACE)

        val cardsScore = Score.optimizedSum(cards)
        val blackjackCardsScore = Score.optimizedSum(blackjackCards)

        assertFalse(cardsScore.isBlackjackNumber())
        assertTrue(blackjackCardsScore.isBlackjackNumber())
    }

    @Test
    fun `카드의 합계가 딜러의 추카 카드 획득 숫자를 만족하는 지 판단한다`() {
        val cards = listOf(HEART_JACK, HEART_SEVEN)
        val availDrawCards = listOf(HEART_ACE, HEART_FIVE)

        val cardsScore = Score.optimizedSum(cards)
        val availDrawCardsScore = Score.optimizedSum(availDrawCards)

        assertFalse(cardsScore.isDrawableCardByDealer())
        assertTrue(availDrawCardsScore.isDrawableCardByDealer())
    }

    @Test
    fun `카드의 합계를 계산한다`() {
        val cards = listOf(HEART_ACE, HEART_SEVEN)
        val expected = Score.from(18)

        val actual = Score.optimizedSum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `A가 존재한다면 최적의 계산을 진행한다`() {
        val cards = listOf(HEART_ACE, CLOVER_ACE, SPADE_ACE, HEART_EIGHT)
        val expected = Score.from(21)

        val actual = Score.optimizedSum(cards)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `스코어의 대소 비교를 한다`() {
        val score = Score.from(10)

        val smallerScore = Score.from(9)
        val sameScore = Score.from(10)
        val biggerScore = Score.from(11)

        assertTrue(score > smallerScore)
        assertTrue(score < biggerScore)
        assertTrue(score == sameScore)
    }
}
