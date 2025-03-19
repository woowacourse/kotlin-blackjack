package blackjack.domain.model.hand

import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_FOUR
import blackjack.domain.HEART_KING
import blackjack.domain.HEART_SIX
import blackjack.domain.HEART_TEN
import blackjack.domain.model.Card
import blackjack.domain.model.Rank
import blackjack.domain.model.Suit
import blackjack.domain.model.hand.state.BlackJack
import blackjack.domain.model.hand.state.Bust
import blackjack.domain.model.hand.state.Hit
import blackjack.domain.model.hand.state.Stay
import blackjack.domain.model.hand.strategy.DealerStay
import blackjack.domain.model.hand.strategy.PlayerStay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HitTest {
    private lateinit var hit: Hit

    @BeforeEach
    fun setUp() {
        hit = Hit(DealerStay(), Hands(HEART_ACE))
    }

    @Test
    fun `Hit 상태는 끝난 상태가 아니다`() {
        assertThat(hit.isFinished()).isFalse()
    }

    @Test
    fun `Hit 상태에서 stay를 반환한다`() {
        assertThat(hit.stay()).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 2장이고 21일 경우 블랙잭을 반환한다`() {
        assertThat(hit.nextState(Card(Suit.HEART, Rank.TEN))).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `딜러 전략일때 카드가 17이상일 경우 스테이를 반환한다`() {
        hit.nextState(HEART_SIX)
        assertThat(hit.nextState(Card(Suit.HEART, Rank.FOUR))).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `플레이어 전략일때 카드가 21일 경우 스테이를 반환한다`() {
        hit = Hit(PlayerStay(), Hands(HEART_ACE))
        hit.nextState(HEART_SIX)
        hit.nextState(HEART_FOUR)
        assertThat(hit.nextState(Card(Suit.HEART, Rank.FOUR))).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 받을때 16이하 일 경우 Hit를 반환한다`() {
        assertThat(hit.nextState(Card(Suit.HEART, Rank.FIVE))).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드를 받을때 21이 넘을 경우 Bust를 반환한다`() {
        hit.nextState(HEART_SIX)
        hit.nextState(HEART_TEN)
        assertThat(hit.nextState(HEART_KING)).isInstanceOf(Bust::class.java)
    }
}
