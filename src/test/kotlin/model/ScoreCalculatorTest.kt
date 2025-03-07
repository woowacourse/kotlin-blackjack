package model

import model.CardsTest.Companion.cardOf
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `보유한 카드의 점수를 계산한다`() {
        val cards = cardOf(
            Card(CardRank.SIX, Shape.CLUB),
            Card(CardRank.SEVEN, Shape.HEART),
        )

        val scoreCalculator = ScoreCalculator(cards)
        val exceptedTotalScore = scoreCalculator.calculateTotalCardScore()
        val result = 13

        Assertions.assertEquals(exceptedTotalScore, result)
    }

    @Test
    fun `ACE가 포함된 카드를 계산할 수 있다`() {
        val cards = cardOf(
            Card(CardRank.QUEEN, Shape.CLUB),
            Card(CardRank.NINE, Shape.HEART),
            Card(CardRank.ACE, Shape.HEART)
        )

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 20)
    }

    @Test
    fun `ACE가 여러개 포함되었을때의 값을 계산할 수 있다`() {
        val cards = cardOf(
            Card(CardRank.ACE, Shape.CLUB),
            Card(CardRank.ACE, Shape.HEART),
            Card(CardRank.ACE, Shape.SPADE),
            Card(CardRank.TEN, Shape.SPADE),
            Card(CardRank.EIGHT, Shape.CLUB)
        )

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 20일 때 ACE는 1이다`() {
        val cards = cardOf(
            Card(CardRank.ACE, Shape.CLUB),
            Card(CardRank.TEN, Shape.SPADE),
            Card(CardRank.TEN, Shape.HEART)
        )

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 10일 때 ACE는 11이다`() {
        val cards =
            cardOf(
                Card(CardRank.ACE, Shape.CLUB),
                Card(CardRank.FIVE, Shape.SPADE),
                Card(CardRank.FIVE, Shape.HEART)
            )

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 21)
    }
}
