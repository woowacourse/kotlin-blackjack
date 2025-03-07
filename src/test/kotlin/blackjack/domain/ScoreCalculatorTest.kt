package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `점수를 계산한다`() {
        // Given
        val scoreCalculator = ScoreCalculator()
        val cards = List(3) { Card.create(CardNumber.ACE, CardPattern.HEART) }

        // When
        val result = scoreCalculator.calculate(cards)

        // Then
        result shouldBe 13
    }
}
