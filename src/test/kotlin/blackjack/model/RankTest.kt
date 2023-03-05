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
        Assertions.assertThat(card.rank.getScore()).isEqualTo(2)
    }

    @Test
    fun `현재 점수가 10 이하인 경우 ACE의 점수는 11이다`() {
        Assertions.assertThat(Rank.ACE.getScore(10)).isEqualTo(11)
    }

    @Test
    fun `현재 점수가 10 초과인 경우 ACE의 점수는 1이다`() {
        Assertions.assertThat(Rank.ACE.getScore(11)).isEqualTo(1)
    }
}
