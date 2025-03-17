package blackjack.domain.model.hand

import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_KING
import blackjack.domain.HEART_SIX
import blackjack.domain.HEART_TEN
import blackjack.domain.model.Card
import blackjack.domain.model.Rank
import blackjack.domain.model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerHitTest {
    private lateinit var dealerHit: DealerHit

    @BeforeEach
    fun setUp() {
        dealerHit = DealerHit(Hands(HEART_ACE))
    }

    @Test
    fun `Hit 상태는 끝난 상태가 아니다`() {
        assertThat(dealerHit.isFinished()).isFalse()
    }

    @Test
    fun `Hit 상태에서 stay를 반환한다`() {
        assertThat(dealerHit.stay()).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 2장이고 21일 경우 블랙잭을 반환한다`() {
        assertThat(dealerHit.nextState(Card(Suit.HEART, Rank.TEN))).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 17이상일 경우 스테이를 반환한다`() {
        dealerHit.nextState(HEART_SIX)
        assertThat(dealerHit.nextState(Card(Suit.HEART, Rank.FOUR))).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 받을때 16이하 일 경우 Hit를 반환한다`() {
        assertThat(dealerHit.nextState(Card(Suit.HEART, Rank.FIVE))).isInstanceOf(DealerHit::class.java)
    }

    @Test
    fun `카드를 받을때 21이 넘을 경우 Bust를 반환한다`() {
        dealerHit.nextState(HEART_SIX)
        dealerHit.nextState(HEART_TEN)
        assertThat(dealerHit.nextState(HEART_KING)).isInstanceOf(Bust::class.java)
    }
}
