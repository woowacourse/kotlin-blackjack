package model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `보유한 카드의 점수를 계산한다`() {
        val card1 = Card(CardRank.SIX, Shape.CLUB)
        val card2 = Card(CardRank.SEVEN, Shape.HEART)

        val cards = Cards(mutableListOf(card1, card2))

        val scoreCalculator = ScoreCalculator(cards.allCards)
        val exceptedTotalScore = scoreCalculator.calculateTotalScore()
        val result = 13

        Assertions.assertEquals(exceptedTotalScore, result)
    }
}
