package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `보유한 카드의 점수를 계산한다`() {
        val card1 = Card(CardRank.SIX, Shape.CLUB)
        val card2 = Card(CardRank.SEVEN, Shape.HEART)

        val cards = Cards(mutableListOf(card1, card2))

        val scoreCalculator = ScoreCalculator(cards)
        val exceptedTotalScore = scoreCalculator.calculateTotalScore()
        val result = 13

        Assertions.assertEquals(exceptedTotalScore, result)
    }

    @Test
    fun `ACE가 포함된 카드를 계산할 수 있다`() {
        val card1 = Card(CardRank.QUEEN, Shape.CLUB)
        val card2 = Card(CardRank.NINE, Shape.HEART)
        val card3 = Card(CardRank.ACE, Shape.HEART)

        val cards = Cards(mutableListOf(card1, card2, card3))

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.totalScore()

        Assertions.assertEquals(totalScore, 20)
    }
}
