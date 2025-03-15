package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import blackjack.domain.state.Ready
import blackjack.domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingStateTest {
    @Test
    fun `Ready에서 draw 하면 Hit으로 전환`() {
        val hand = Hand(listOf())
        val state = Ready(hand).draw(TestFixture.ClubSix)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, bust면 Bust로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubNine, TestFixture.ClubEight))
        val state = Hit(hand).draw(TestFixture.ClubSeven)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, blackjack면 Blackjack로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubAce))
        val state = Hit(hand).draw(TestFixture.ClubTen)
        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때 bust나 blackjack이 아니면 Hit으로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubFour))
        val state = Hit(hand).draw(TestFixture.ClubFive)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 stay 했을 때 stay로 전환`() {
        val hand = Hand(listOf(TestFixture.ClubQueen, TestFixture.ClubKing))
        val state = Hit(hand).stay()
        assertThat(state).isInstanceOf(Stay::class.java)
    }
}
