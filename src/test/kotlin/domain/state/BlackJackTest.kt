package domain.state

import domain.Dummy.CLOVER_TWO
import domain.Dummy.makeHandOfCards
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BlackJackTest {
    @Test
    fun `nextState함수를 호출하면 StateException이 발생한다`() {
        val blackJack = BlackJack(makeHandOfCards(10, 1))

        assertThrows<IllegalStateException> { blackJack.nextState(CLOVER_TWO) }.shouldHaveMessage("이미 끝났습니다. 다음 State가 없습니다.")
    }

    @Test
    fun `플레이어와 딜러가 모두 블랙잭일때 플레이어의 수익은 0이다`() {
        val playerState = BlackJack(makeHandOfCards(10, 1))
        val otherState = BlackJack(makeHandOfCards(10, 1))

        assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(0.0)
    }

    @Test
    fun `플레이어만 블랙잭일때 플레이어의 수익은 베팅금액의 1점5배이다`() {
        val playerState = BlackJack(makeHandOfCards(10, 1))
        val otherState = Stay(makeHandOfCards(2, 1))

        assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(1500.0)
    }
}
