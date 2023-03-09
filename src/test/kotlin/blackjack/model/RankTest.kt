package blackjack.model

import model.cards.Rank
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `현재 점수가 10 이하인 경우 ACE의 점수는 11이다`() {
        Assertions.assertThat(Rank.ACE.getScore(10)).isEqualTo(11)
    }

    @Test
    fun `현재 점수가 10 초과인 경우 ACE의 점수는 1이다`() {
        Assertions.assertThat(Rank.ACE.getScore(11)).isEqualTo(1)
    }
}
