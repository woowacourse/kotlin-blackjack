package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.person.Hand
import blackjack.domain.score.ScoreCalculator
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ScoreCalculatorTest {
    @Test
    fun `패에 들고 있는 카드의 총 점수를 계산할 수 있다`() {
        val hand = Hand()
        hand.addCard(Card.create(CardNumber.ACE, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.JACK, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.ACE, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.QUEEN, CardPattern.HEART))
        hand.addCard(Card.create(CardNumber.ACE, CardPattern.HEART))

        val score = ScoreCalculator.calculate(hand)

        score shouldBe 23
    }
}
