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
}
