package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `점수를 계산한다`() {
        val cards = List(3) { Card.create(CardNumber.ACE, CardPattern.HEART) }

        val result = Score(cards).value

        result shouldBe 13
    }
}
