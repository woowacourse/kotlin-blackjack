package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `랭크의 종류는 Ace, 숫자, 캐릭터이다`() {
        assertThat(Ace is Rank).isTrue()
        assertThat(Number(3) is Rank).isTrue()
        assertThat(Face.JACK is Rank).isTrue()
    }
}
