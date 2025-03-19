package blackjack.domain.state

import blackjack.domain.ClubSix
import blackjack.domain.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ReadyTest {
    @Test
    fun `Ready에서 draw 하면 Hit으로 전환`() {
        val hand = Hand(listOf())
        val state = Ready(hand).draw(ClubSix)
        assertThat(state).isInstanceOf(Hit::class.java)
    }
}