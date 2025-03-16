package model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import util.TestCards

class ScoreCalculatorTest {
    @Test
    fun `보유한 카드의 점수를 계산한다`() {
        val cards = listOf(TestCards.CLUB_SIX, TestCards.HEART_SEVEN)

        val scoreCalculator = ScoreCalculator(Hand(cards))
        val expectedTotalScore = scoreCalculator.calculateTotalCardScore()

        assertEquals(expectedTotalScore, 13)
    }

    @Test
    fun `ACE가 포함된 카드를 계산할 수 있다`() {
        val cards = listOf(TestCards.CLUB_QUEEN, TestCards.HEART_NINE, TestCards.HEART_ACE)

        val scoreCalculator = ScoreCalculator(Hand(cards))
        val totalScore = scoreCalculator.calculateTotalCardScore()

        assertEquals(totalScore, 20)
    }

    @Test
    fun `ACE가 여러개 포함되었을때의 값을 계산할 수 있다`() {
        val cards =
            listOf(
                TestCards.CLUB_ACE,
                TestCards.HEART_ACE,
                TestCards.SPADE_ACE,
                TestCards.SPADE_TEN,
                TestCards.SPADE_EIGHT,
            )

        val scoreCalculator = ScoreCalculator(Hand(cards))
        val totalScore = scoreCalculator.calculateTotalCardScore()

        assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 20일 때 ACE는 1이다`() {
        val cards = listOf(TestCards.CLUB_ACE, TestCards.SPADE_TEN, TestCards.HEART_TEN)

        val scoreCalculator = ScoreCalculator(Hand(cards))
        val totalScore = scoreCalculator.calculateTotalCardScore()

        assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 10일 때 ACE는 11이다`() {
        val cards = listOf(TestCards.CLUB_ACE, TestCards.SPADE_FIVE, TestCards.HEART_FIVE)

        val scoreCalculator = ScoreCalculator(Hand(cards))
        val totalScore = scoreCalculator.calculateTotalCardScore()

        assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE가 포함되었을 때 18임을 확인할 수 있다`() {
        val cards = listOf(TestCards.CLUB_ACE, TestCards.SPADE_ACE, TestCards.SPADE_ACE, TestCards.HEART_FIVE)

        val scoreCalculator = ScoreCalculator(Hand(cards))
        val totalScore = scoreCalculator.calculateTotalCardScore()

        assertEquals(totalScore, 18)
    }

    @Test
    fun `ACE가 포함되었을 때 21임을 확인할 수 있다`() {
        val cards1 = listOf(TestCards.CLUB_ACE, TestCards.SPADE_ACE, TestCards.HEART_NINE)
        val cards2 = listOf(TestCards.CLUB_ACE, TestCards.HEART_KING)

        val scoreCalculator1 = ScoreCalculator(Hand(cards1))
        val scoreCalculator2 = ScoreCalculator(Hand(cards2))

        val totalScore1 = scoreCalculator1.calculateTotalCardScore()
        val totalScore2 = scoreCalculator2.calculateTotalCardScore()

        assertEquals(totalScore1, 21)
        assertEquals(totalScore2, 21)
    }
}
