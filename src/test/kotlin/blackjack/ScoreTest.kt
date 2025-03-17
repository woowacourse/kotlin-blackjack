package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.gameResult.GameResult
import blackjack.domain.score.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {
    @Test
    fun `두 Score끼리 비교할 때 value의 값을 기준으로 리턴한다(동등)`() {
        val score1 =
            Score(
                listOf(Card.of(Rank.ACE, Suit.CLUB), Card.of(Rank.THREE, Suit.CLUB)),
            )
        val score2 =
            Score(
                listOf(Card.of(Rank.ACE, Suit.CLUB), Card.of(Rank.THREE, Suit.CLUB)),
            )
        assertThat(score1.isEqualTo(score2)).isTrue()
    }

    @Test
    fun `두 Score끼리 비교할 때 value의 값을 기준으로 리턴한다(대소비교)`() {
        val score1 =
            Score(
                listOf(Card.of(Rank.NINE, Suit.CLUB), Card.of(Rank.TEN, Suit.CLUB)),
            )
        val score2 =
            Score(
                listOf(Card.of(Rank.EIGHT, Suit.CLUB), Card.of(Rank.TEN, Suit.CLUB)),
            )
        assertThat(score1 > score2).isTrue()
        assertThat(score2 < score1).isTrue()
    }

    @Test
    fun `Score와 Int끼리 비교할 때 value의 값을 기준으로 리턴한다(대소비교)`() {
        val score1 =
            Score(
                listOf(Card.of(Rank.NINE, Suit.CLUB), Card.of(Rank.TEN, Suit.CLUB)),
            )
        assertThat(score1 > 18).isTrue()
        assertThat(score1 < 20).isTrue()
    }

    @Test
    fun `Score의 compare메서드는 대소비교 값을 바탕으로 GameResult 를 반환한다`() {
        val score1 =
            Score(
                listOf(Card.of(Rank.NINE, Suit.CLUB), Card.of(Rank.TEN, Suit.CLUB)),
            )
        val score2 =
            Score(
                listOf(Card.of(Rank.EIGHT, Suit.CLUB), Card.of(Rank.TEN, Suit.CLUB)),
            )
        assertThat(score1.compare(score2)).isEqualTo(GameResult.WIN)
        assertThat(score2.compare(score1)).isEqualTo(GameResult.LOSE)
    }
}
