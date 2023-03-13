package domain.state

import domain.Dummy.CLOVER_TWO
import domain.Dummy.makeHandOfCards
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StayTest {
    @Test
    fun `toStay함수를 호출하면 StateException이 발생한다`() {
        val stay = Stay(makeHandOfCards(10, 2))

        assertThrows<IllegalStateException> { stay.toStay() }
            .shouldHaveMessage("이미 끝났습니다. stay를 호출할 수 없습니다.")
    }

    @Test
    fun `nextState함수를 호출하면 StateException이 발생한다`() {
        val stay = Stay(makeHandOfCards(10, 2))

        assertThrows<IllegalStateException> { stay.nextState(CLOVER_TWO) }
            .shouldHaveMessage("이미 끝났습니다. 다음 State가 없습니다.")
    }

    @Test
    fun `플레이어가 스테이이고 딜러가 블랙잭일때 플레이어의 수익은 베팅금액의 -1배이다`() {
        val playerState = Stay(makeHandOfCards(2, 1))
        val otherState = BlackJack(makeHandOfCards(10, 1))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(-1000.0)
    }

    @Test
    fun `플레이어가 스테이이고 딜러가 버스트일때 플레이어의 수익은 배팅금액만큼이다`() {
        val playerState = Stay(makeHandOfCards(2, 1))
        val otherState = Bust(makeHandOfCards(10, 10, 2))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(1000.0)
    }

    @Test
    fun `모두 스테이이고 플레이어의 카드합이 더 클때 플레이어의 수익은 베팅금액만큼이다`() {
        val playerState = Stay(makeHandOfCards(2, 1))
        val otherState = Stay(makeHandOfCards(2, 3))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(1000.0)
    }

    @Test
    fun `모두 스테이이고 딜러의 카드합이 더 클때 플레이어의 수익은 베팅금액의 -1배이다`() {
        val playerState = Stay(makeHandOfCards(2, 1))
        val otherState = Stay(makeHandOfCards(10, 4))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(-1000.0)
    }

    @Test
    fun `모두 스테이이고 카드합이 같을 때 플레이어의 수익은 0이다`() {
        val playerState = Stay(makeHandOfCards(2, 5))
        val otherState = Stay(makeHandOfCards(3, 4))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(0.0)
    }
}
