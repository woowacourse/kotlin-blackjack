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
        val exceptedTotalScore = scoreCalculator.calculateTotalCardScore()
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
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 20)
    }

    @Test
    fun `ACE가 여러개 포함되었을때의 값을 계산할 수 있다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.ACE, Shape.HEART)
        val card3 = Card(CardRank.ACE, Shape.SPADE)
        val card4 = Card(CardRank.TEN, Shape.SPADE)
        val card5 = Card(CardRank.EIGHT, Shape.CLUB)

        val cards = Cards(mutableListOf(card1, card2, card3, card4, card5))

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 20일 때 ACE는 1이다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.TEN, Shape.SPADE)
        val card3 = Card(CardRank.TEN, Shape.HEART)

        val cards = Cards(mutableListOf(card1, card2, card3))

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 21)
    }

    @Test
    fun `ACE카드를 제외하고 보유한 카드의 합이 10일 때 ACE는 11이다`() {
        val card1 = Card(CardRank.ACE, Shape.CLUB)
        val card2 = Card(CardRank.FIVE, Shape.SPADE)
        val card3 = Card(CardRank.FIVE, Shape.HEART)

        val cards = Cards(mutableListOf(card1, card2, card3))

        val scoreCalculator = ScoreCalculator(cards)
        val totalScore = scoreCalculator.calculateTotalCardScore()

        Assertions.assertEquals(totalScore, 21)
    }
}
