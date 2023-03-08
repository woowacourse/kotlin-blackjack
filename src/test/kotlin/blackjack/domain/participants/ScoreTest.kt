package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScoreTest {

    @Test
    fun `버스트 나지 않는 최댓값을 반환할 수 있다`() {
        val cards = Cards(
            setOf(
                Card(CardMark.CLOVER, CardValue.ACE),
                Card(CardMark.SPADE, CardValue.ACE),
            ),
        )

        val score = Score(cards)

        assertThat(score.score).isEqualTo(12)
    }

    @Test
    fun `버스트 날 때 ACE를 1로 계산한다`() {
        val cards = Cards(
            setOf(
                Card(CardMark.CLOVER, CardValue.ACE),
                Card(CardMark.CLOVER, CardValue.KING),
                Card(CardMark.CLOVER, CardValue.QUEEN),
                Card(CardMark.SPADE, CardValue.QUEEN),
            ),
        )

        val score = Score(cards)

        assertThat(score.maxScore).isEqualTo(31)
    }
}
