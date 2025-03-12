package blackjack.domain

import blackjack.domain.Rank.AceRank
import blackjack.domain.Rank.FaceRank
import blackjack.domain.Rank.NumberRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `랭크의 종류는 Ace, 숫자, 캐릭터이다`() {
        assertThat(AceRank is Rank).isTrue()
        assertThat(NumberRank.THREE is Rank).isTrue()
        assertThat(FaceRank.JACK is Rank).isTrue()
    }
}
