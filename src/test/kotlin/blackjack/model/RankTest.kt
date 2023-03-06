package blackjack.model

import model.Card
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `카드에 맞는 점수를 반환한다`() {
        val card = Card(Rank.DEUCE, Suit.CLOVER)
        Assertions.assertThat(card.rank.score).isEqualTo(2)
    }
}
