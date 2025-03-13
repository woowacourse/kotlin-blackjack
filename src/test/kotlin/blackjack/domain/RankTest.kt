package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `랭크의 종류는 Ace, 숫자, 캐릭터이다`() {
        val ace = Ace()
        val number = Number(3)
        val character = Character.JACK
        assertThat(ace is Rank).isTrue()
        assertThat(number is Rank).isTrue()
        assertThat(character is Rank).isTrue()
    }
}
