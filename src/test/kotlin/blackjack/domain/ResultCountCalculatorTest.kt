package blackjack.domain

import blackjack.model.card.Card
import blackjack.model.card.CardRank.ACE
import blackjack.model.card.CardRank.JACK
import blackjack.model.card.CardRank.KING
import blackjack.model.card.CardRank.NINE
import blackjack.model.card.CardRank.QUEEN
import blackjack.model.card.CardRank.TWO
import blackjack.model.card.CardSuit.CLUB
import blackjack.model.rule.ScoreCalculator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResultCountCalculatorTest {
    private lateinit var scoreCalculator: ScoreCalculator

    @BeforeEach
    fun setup() {
        scoreCalculator = ScoreCalculator()
    }

    @Test
    fun `핸드에 2클로버 한 장을 가지고 있으면 2점을 반환한다`() {
        // given
        val expectedScore = 2
        val card = Card(TWO, CLUB)

        // when
        val score = scoreCalculator.score(listOf(card))

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `핸드에 에이스를 포함한 값이 21이 넘으면 에이스를 1로 바꾼다`() {
        // given
        val expectedScore = 2
        val card = Card(TWO, CLUB)

        // when
        val score = scoreCalculator.score(listOf(card))

        // then
        assertThat(score).isEqualTo(expectedScore)
    }

    @Test
    fun `21점이 초과하면 Bust를 반환한다`() {
        // given
        val card1 = Card(QUEEN, CLUB)
        val card2 = Card(KING, CLUB)
        val card3 = Card(JACK, CLUB)
        val cards = listOf(card1, card2, card3)

        // when
        val score = scoreCalculator.score(cards)

        // then
        assertThat(scoreCalculator.isBust(score)).isTrue()
    }

    @Test
    fun `ACE 1장, Q 1장, 9 1장을 가지고 있으면 20점을 반환한다`() {
        // given
        val card1 = Card(ACE, CLUB)
        val card2 = Card(QUEEN, CLUB)
        val card3 = Card(NINE, CLUB)
        val cards = listOf(card1, card2, card3)
        val expectedScore = 20

        // when & then
        assertThat(scoreCalculator.score(cards)).isEqualTo(expectedScore)
    }
}
