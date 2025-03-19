package blackjack.domain.state

import blackjack.domain.ClubAce
import blackjack.domain.ClubEight
import blackjack.domain.ClubFive
import blackjack.domain.ClubFour
import blackjack.domain.ClubKing
import blackjack.domain.ClubNine
import blackjack.domain.ClubQueen
import blackjack.domain.ClubSeven
import blackjack.domain.ClubTen
import blackjack.domain.Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `Hit에서 draw 했을 때, bust면 Bust로 전환`() {
        val hand = Hand(listOf(ClubNine, ClubEight))
        val state = Hit(hand).draw(ClubSeven)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때, blackjack면 Blackjack로 전환`() {
        val hand = Hand(listOf(ClubAce))
        val state = Hit(hand).draw(ClubTen)
        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `Hit에서 draw 했을 때 bust나 blackjack이 아니면 Hit으로 전환`() {
        val hand = Hand(listOf(ClubFour))
        val state = Hit(hand).draw(ClubFive)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Hit에서 stay 했을 때 stay로 전환`() {
        val hand = Hand(listOf(ClubQueen, ClubKing))
        val state = Hit(hand).stay()
        assertThat(state).isInstanceOf(Stay::class.java)
    }
}