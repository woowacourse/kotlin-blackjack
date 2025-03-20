package blackjack.domain.model

import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_SEVEN
import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_FOUR
import blackjack.domain.HEART_KING
import blackjack.domain.HEART_TEN
import blackjack.domain.model.hand.state.BlackJack
import blackjack.domain.model.hand.state.Bust
import blackjack.domain.model.hand.state.Hit
import blackjack.domain.model.hand.state.Stay
import blackjack.domain.model.playing.PlayingDealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayingDealerTest {
    private lateinit var playingDealer: PlayingDealer

    @BeforeEach
    fun setUp() {
        playingDealer = PlayingDealer(HEART_ACE)
    }

    @Test
    fun `딜러는 16 이하일 경우 HIT 상태를 반환한다`() {
        playingDealer.acceptCard(Card(Suit.HEART, Rank.FIVE)) // score 16
        assertThat(playingDealer.handsState).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `딜러는 21이고 두장일 경우에 BLACKJACK 상태를 반환한다`() {
        playingDealer.acceptCard(HEART_KING)
        assertThat(playingDealer.handsState).isInstanceOf(BlackJack::class.java) // score 21
    }

    @Test
    fun `딜러는 21 초과일 경우 BUST를 반환한다`() {
        playingDealer.acceptCard(HEART_FOUR)
        playingDealer.acceptCard(CLUB_KING)
        playingDealer.acceptCard(HEART_TEN)
        assertThat(playingDealer.handsState).isInstanceOf(Bust::class.java) // score 24
    }

    @Test
    fun `딜러는 17이상 21 미만일 경우 STAY를 반환한다`() {
        playingDealer.acceptCard(CLUB_SEVEN) // score 17
        assertThat(playingDealer.handsState).isInstanceOf(Stay::class.java)
    }
}
