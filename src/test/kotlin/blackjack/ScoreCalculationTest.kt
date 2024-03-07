package blackjack

import blackjack.model.card.Card
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import blackjack.model.card.Suit
import blackjack.model.game.ScoreCalculation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreCalculationTest {
    @Test
    fun `스코어 계산하기`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.SIX, Suit.SPADES))
        val hand = Hand(cards)
        val totalScore = ScoreCalculation.calculate(hand)

        assertThat(totalScore).isEqualTo(17)
    }

    @Test
    fun `만약 버스트 and ACE 가지고 있으면 ACE를 1로 변환`() {
        val cards = mutableListOf(Card(Denomination.ACE, Suit.HEARTS), Card(Denomination.ACE, Suit.SPADES))
        val hand = Hand(cards)
        hand.aceCount = 1
        val totalScore = ScoreCalculation.calculate(hand)
        assertThat(totalScore).isEqualTo(12)
    }
}
