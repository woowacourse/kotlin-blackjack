package blackjack.domain.model

import blackjack.domain.model.hand.HandState
import blackjack.domain.model.playing.PlayingDealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayingDealerTest {
    private lateinit var playingDealer: PlayingDealer

    @BeforeEach
    fun setUp() {
        playingDealer = PlayingDealer(Card(Suit.HEART, Rank.ACE))
    }

    @Test
    fun `딜러는 16 이하일 경우 HIT 상태를 반환한다`() {
        val handState = playingDealer.getHandsState()
        val actual = HandState.HIT
        assertThat(handState).isEqualTo(actual)
    }

    @Test
    fun `딜러는 21이고 두장일 경우에 BLACKJACK 상태를 반환한다`() {
        playingDealer = PlayingDealer(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.KING))
        val handState = playingDealer.getHandsState()
        val actual = HandState.BLACKJACK
        assertThat(handState).isEqualTo(actual)
    }

    @Test
    fun `딜러는 21 초과일 경우 BUST를 반환한다`() {
        playingDealer = PlayingDealer(Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SIX))
        val handState = playingDealer.getHandsState()
        val actual = HandState.BUST
        assertThat(handState).isEqualTo(actual)
    }

    @Test
    fun `딜러는 17이상 21 미만일 경우 STAY를 반환한다`() {
        playingDealer = PlayingDealer(Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SEVEN))
        val handState = playingDealer.getHandsState()
        val actual = HandState.STAY
        assertThat(handState).isEqualTo(actual)
    }
}
