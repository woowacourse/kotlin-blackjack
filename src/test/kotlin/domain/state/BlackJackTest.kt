package domain.state

import domain.Dummy.CLOVER_ACE
import domain.Dummy.CLOVER_KING
import domain.Dummy.CLOVER_TWO
import domain.card.HandOfCards
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlackJackTest {
    @Test
    fun `toStay함수를 호출하면 StateException이 발생한다`() {
        val blackJack = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))

        assertThrows<IllegalStateException> { blackJack.toStay() }.shouldHaveMessage("이미 끝났습니다. stay를 호출할 수 없습니다.")
    }

    @Test
    fun `nextState함수를 호출하면 StateException이 발생한다`() {
        val blackJack = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))

        assertThrows<IllegalStateException> { blackJack.nextState { CLOVER_TWO } }.shouldHaveMessage("이미 끝났습니다. 다음 State가 없습니다.")
    }

    @Test
    fun `플레이어와 딜러가 모두 블랙잭일때 플레이어의 수익은 0이다`() {
        val playerState = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))
        val otherState = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))

        assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(0.0)
    }

    @Test
    fun `플레이어만 블랙잭일때 플레이어의 수익은 베팅금액의 1점5배이다`() {
        val playerState = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))
        val otherState = Stay(HandOfCards(CLOVER_TWO, CLOVER_ACE))

        assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(1500.0)
    }
}
