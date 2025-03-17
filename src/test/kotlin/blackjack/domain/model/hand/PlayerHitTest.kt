package blackjack.domain.model.hand

import blackjack.domain.HEART_ACE
import blackjack.domain.HEART_FOUR
import blackjack.domain.HEART_KING
import blackjack.domain.HEART_SIX
import blackjack.domain.HEART_TEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerHitTest {
    private lateinit var playerHit: PlayerHit

    @BeforeEach
    fun setUp() {
        playerHit = PlayerHit(Hands(HEART_ACE))
    }

    @Test
    fun `Hit 상태는 끝난 상태가 아니다`() {
        assertThat(playerHit.isFinished()).isFalse()
    }

    @Test
    fun `Hit 상태에서 stay를 반환한다`() {
        assertThat(playerHit.stay()).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 2장이고 21일 경우 블랙잭을 반환한다`() {
        assertThat(playerHit.nextState(HEART_TEN)).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `카드를 받을때 카드가 3장 이상이고 21일 경우 스테이를 반환한다`() {
        playerHit.nextState(HEART_SIX)
        assertThat(playerHit.nextState(HEART_FOUR)).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 받을때 21이하 일 경우 Hit를 반환한다`() {
        assertThat(playerHit.nextState(HEART_SIX)).isInstanceOf(PlayerHit::class.java)
    }

    @Test
    fun `카드를 받을때 21이 넘을 경우 Bust를 반환한다`() {
        playerHit.nextState(HEART_SIX)
        playerHit.nextState(HEART_TEN)
        assertThat(playerHit.nextState(HEART_KING)).isInstanceOf(Bust::class.java)
    }
}
