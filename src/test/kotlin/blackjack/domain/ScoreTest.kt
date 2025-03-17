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

        val result = Score.create(cards).value

        result shouldBe 13
    }

    @Test
    fun `isBlackJackScore() - 점수가 21점일 때 True를 반환한다`() {
        val cards = generateCustomCards(listOf(CardNumber.JACK, CardNumber.ACE))
        val score = Score.create(cards)

        score.isBlackJackScore() shouldBe true
    }

    @Test
    fun `isBustScore() - 점수가 21점보다 클 때 True를 반환한다`() {
        val cards = generateCustomCards(listOf(CardNumber.JACK, CardNumber.JACK, CardNumber.SIX))
        val score = Score.create(cards)

        score.isBustScore() shouldBe true
    }

    @Test
    fun `isDealerStayScore() - 점수가 16점보다 클 때 True를 반환한다`() {
        val cards = generateCustomCards(listOf(CardNumber.JACK, CardNumber.SEVEN))
        val score = Score.create(cards)

        score.isDealerStayScore() shouldBe true
    }
}
